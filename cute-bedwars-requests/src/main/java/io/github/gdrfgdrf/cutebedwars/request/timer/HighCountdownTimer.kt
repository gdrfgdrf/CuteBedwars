package io.github.gdrfgdrf.cutebedwars.request.timer

import io.github.gdrfgdrf.cutebedwars.commons.extension.*
import io.github.gdrfgdrf.cutebedwars.request.Request
import io.github.gdrfgdrf.cutebedwars.request.enums.RequestStatuses
import io.github.gdrfgdrf.cuteframework.utils.thread.ThreadPoolService
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit

object HighCountdownTimer {
    var stop = false
        private set
    val requests = ConcurrentHashMap<Request, Long>()

    fun start() {
        "Starting high countdown timer".logInfo()

        stop = false
        ThreadPoolService.newTask(HighCountdownWorker)
    }

    fun reset() {
        "Resetting high countdown timer".logInfo()

        stop = true
        requests.clear()
    }

    fun put(request: Request) {
        requests[request] = System.currentTimeMillis()
        request.status = RequestStatuses.READY
    }

    fun remove(request: Request) {
        requests.remove(request)
    }
}

internal object HighCountdownWorker : Runnable {
    override fun run() {
        "High countdown worker is running".logInfo()

        while (!HighCountdownTimer.stop) {
            runCatching {
                val now = System.currentTimeMillis()

                HighCountdownTimer.requests.forEach { (request, startTime) ->
                    if (request.status == RequestStatuses.STOPPED || request.status == RequestStatuses.RUNNING) {
                        HighCountdownTimer.requests.remove(request)
                        return@forEach
                    }
                    request.status = RequestStatuses.TRY_RUNNING

                    val convertedTimeout = TimeUnit.MILLISECONDS.convert(request.timeout, request.timeUnit)
                    if (now - startTime >= convertedTimeout) {
                        HighCountdownTimer.requests.remove(request)
                        request.status = RequestStatuses.RUNNING

                        ThreadPoolService.newTask {
                            request.endRun(request)
                            request.status = RequestStatuses.STOPPED
                        }
                    }

                    if (now - request.lastEachSecondRun >= 1000) {
                        ThreadPoolService.newTask {
                            request.passedSecond++
                            request.eachSecond(request)
                            request.lastEachSecondRun = System.currentTimeMillis()
                        }
                    }

                    if (request.status != RequestStatuses.RUNNING) {
                        request.status = RequestStatuses.WAIT_NEXT_ROUND
                    }

                    if (HighCountdownTimer.requests.isEmpty()) {
                        sleepSafely(50)
                    }
                }
            }.onFailure {
                "An error occurred for the high countdown worker".logError(it)
            }
        }

        "High countdown worker is terminated".logInfo()
    }
}