package io.github.gdrfgdrf.cutebedwars.request.timer

import io.github.gdrfgdrf.cutebedwars.commons.extension.launchIO
import io.github.gdrfgdrf.cutebedwars.commons.extension.logInfo
import io.github.gdrfgdrf.cutebedwars.commons.extension.sleepSafely
import io.github.gdrfgdrf.cutebedwars.request.Request
import io.github.gdrfgdrf.cutebedwars.request.enums.RequestStatuses
import io.github.gdrfgdrf.cuteframework.utils.thread.ThreadPoolService
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit

object LowCountdownTimer {
    var stop = false
        private set
    val requests = ConcurrentHashMap<Request, Long>()

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

    fun put(request: Request) {
        requests[request] = System.currentTimeMillis()
        request.status = RequestStatuses.READY
    }

}

internal object LowCountdownWorker : Runnable {
    @OptIn(DelicateCoroutinesApi::class)
    override fun run() {
        "Low countdown worker is running".logInfo()

        while (!LowCountdownTimer.stop) {
            runCatching {
                val now = System.currentTimeMillis()

                LowCountdownTimer.requests.forEach { (request, startTime) ->
                    if (request.status == RequestStatuses.STOPPED) {
                        LowCountdownTimer.requests.remove(request)
                        return@forEach
                    }
                    request.status = RequestStatuses.TRY_RUNNING

                    val convertedTimeout = TimeUnit.MILLISECONDS.convert(request.timeout, request.timeUnit)
                    if (now - startTime >= convertedTimeout) {
                        LowCountdownTimer.requests.remove(request)
                        GlobalScope.launchIO {
                            request.endRun(request)
                            request.status = RequestStatuses.STOPPED
                        }

                        request.status = RequestStatuses.RUNNING
                    }

                    if (now - request.lastEachSecondRun >= 1000) {
                        GlobalScope.launchIO {
                            request.passedSecond++
                            request.eachSecond(request)
                            request.lastEachSecondRun = System.currentTimeMillis()
                        }
                    }

                    if (request.status != RequestStatuses.RUNNING) {
                        request.status = RequestStatuses.WAIT_NEXT_ROUND
                    }

                    sleepSafely(50)
                }

            }.onFailure {
                "An error occurred for the low countdown worker".logInfo()
            }
        }

        "Low countdown worker is terminated".logInfo()
    }

}