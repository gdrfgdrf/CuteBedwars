package io.github.gdrfgdrf.cutebedwars.request.timer

import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IThreadPoolService
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IRequestStatuses
import io.github.gdrfgdrf.cutebedwars.abstracts.requests.IRequest
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logError
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.sleepSafely
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit

object HighCountdownTimer {
    var stop = false
        private set
    val requests = ConcurrentHashMap<IRequest, Long>()

    fun start() {
        "Starting high countdown timer".logInfo()

        stop = false
        IThreadPoolService.instance().newTask(HighCountdownWorker)
    }

    fun reset() {
        "Resetting high countdown timer".logInfo()

        stop = true
        requests.clear()
    }

    fun put(request: IRequest) {
        requests[request] = System.currentTimeMillis()
        request.status(IRequestStatuses.valueOf("READY"))
    }

    fun remove(request: IRequest) {
        requests.remove(request)
    }
}

internal object HighCountdownWorker : Runnable {
    private val threadPoolService = IThreadPoolService.instance()

    override fun run() {
        "High countdown worker is running".logInfo()

        while (!HighCountdownTimer.stop) {
            runCatching {
                val now = System.currentTimeMillis()

                HighCountdownTimer.requests.forEach { (request, startTime) ->
                    if (request.status() == IRequestStatuses.valueOf("STOPPED") || request.status() == IRequestStatuses.valueOf("RUNNING")) {
                        HighCountdownTimer.requests.remove(request)
                        return@forEach
                    }
                    request.status(IRequestStatuses.valueOf("TRY_RUNNING"))

                    val convertedTimeout = TimeUnit.MILLISECONDS.convert(request.timeout(), request.timeUnit())
                    if (now - startTime >= convertedTimeout) {
                        request.status(IRequestStatuses.valueOf("RUNNING"))
                        HighCountdownTimer.requests.remove(request)

                        threadPoolService.newTask {
                            request.endRun()(request)
                            request.status(IRequestStatuses.valueOf("STOPPED"))
                        }

                        return@forEach
                    }

                    if (now - request.lastEachSecondRun() >= 1000) {
                        threadPoolService.newTask {
                            request.passedSecond(request.passedSecond() + 1)
                            request.eachSecond()(request)
                            request.lastEachSecondRun(System.currentTimeMillis())
                        }
                    }

                    if (request.status() != IRequestStatuses.valueOf("RUNNING")) {
                        request.status(IRequestStatuses.valueOf("WAIT_NEXT_ROUND"))
                    }
                }

                if (HighCountdownTimer.requests.isEmpty()) {
                    sleepSafely(50)
                }
            }.onFailure {
                "An error occurred for the high countdown worker".logError(it)
            }
        }

        "High countdown worker is terminated".logInfo()
    }
}