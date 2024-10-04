package io.github.gdrfgdrf.cutebedwars.utils.thread

import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IThreadPoolService
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy
import java.util.concurrent.TimeUnit

@ServiceImpl("thread_pool")
object ThreadPoolService : IThreadPoolService {
    private var EXECUTOR_SERVICE: ThreadPoolExecutor? = null

    private fun prepare() {
        "Preparing execute service".logInfo()

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

    override fun newTask(runnable: () -> Unit) {
        if (EXECUTOR_SERVICE == null) {
            prepare()
        }
        EXECUTOR_SERVICE?.execute(runnable)
    }

    override fun newTask(runnable: Runnable) {
        if (EXECUTOR_SERVICE == null) {
            prepare()
        }
        EXECUTOR_SERVICE?.execute(runnable)
    }

    override fun terminate() {
        "Terminating the execute service".logInfo()
        EXECUTOR_SERVICE?.shutdownNow()
        EXECUTOR_SERVICE = null
    }

}