package io.github.gdrfgdrf.cutebedwars.commons.utils.thread

import io.github.gdrfgdrf.cutebedwars.commons.extension.logInfo
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy
import java.util.concurrent.TimeUnit

object ThreadPoolService {
    private var EXECUTOR_SERVICE: ThreadPoolExecutor? = null

    private fun prepare() {
        EXECUTOR_SERVICE = ThreadPoolExecutor(
            20,
            100,
            0L,
            TimeUnit.MILLISECONDS,
            ArrayBlockingQueue(1024),
            NamedThreadFactory,
            AbortPolicy()
        )
    }

    fun newTask(runnable: () -> Unit) {
        if (EXECUTOR_SERVICE == null) {
            prepare()
        }
        EXECUTOR_SERVICE?.execute(runnable)
    }

    fun terminate() {
        EXECUTOR_SERVICE?.shutdownNow()
        "Execute service terminated".logInfo()
    }

}