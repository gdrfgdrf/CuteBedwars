package io.github.gdrfgdrf.cutebedwars.request.timer

import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IThreadPoolService
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IRequestStatuses
import io.github.gdrfgdrf.cutebedwars.abstracts.requests.IRequest
import io.github.gdrfgdrf.cutebedwars.utils.extension.logError
import io.github.gdrfgdrf.cutebedwars.utils.extension.logInfo
import io.github.gdrfgdrf.cutebedwars.utils.extension.sleepSafely
import io.github.gdrfgdrf.cuteframework.utils.thread.ThreadPoolService
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit

object LowCountdownTimer {
    var stop = false
        private set
    val requests = ConcurrentHashMap<IRequest, Long>()

    fun start() {
        "Starting low countdown timer".logInfo()

        stop = false
        ThreadPoolService.newTask(LowCountdownWorker)
    }

    fun reset() {
        "Resetting low countdown timer".logInfo()

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

internal object LowCountdownWorker : Runnable {
    private val threadPoolService = IThreadPoolService.get()

    override fun run() {
        "Low countdown worker is running".logInfo()

        while (!LowCountdownTimer.stop) {
            runCatching {
                val now = System.currentTimeMillis()

                LowCountdownTimer.requests.forEach { (request, startTime) ->
                    if (request.status() == IRequestStatuses.valueOf("STOPPED") || request.status() == IRequestStatuses.valueOf("RUNNING")) {
                        LowCountdownTimer.requests.remove(request)
                        return@forEach
                    }
                    request.status(IRequestStatuses.valueOf("TRY_RUNNING"))

                    val convertedTimeout = TimeUnit.MILLISECONDS.convert(request.timeout(), request.timeUnit())
                    if (now - startTime >= convertedTimeout) {
                        LowCountdownTimer.requests.remove(request)
                        request.status(IRequestStatuses.valueOf("RUNNING"))

                        threadPoolService.newTask {
                            request.endRun()(request)
                            request.status(IRequestStatuses.valueOf("STOPPED"))
                        }
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

                    sleepSafely(50)
                }

            }.onFailure {
                "An error occurred for the low countdown worker".logError(it)
            }
        }

        "Low countdown worker is terminated".logInfo()
    }

}