package io.github.gdrfgdrf.cutebedwars.utils.thread

import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IIConfig
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IThreadPoolService
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.cutebedwars.beans.pojo.config.ThreadPoolServiceImpl
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy
import java.util.concurrent.TimeUnit

@ServiceImpl("thread_pool")
object ThreadPoolService : IThreadPoolService {
    private var EXECUTOR_SERVICE: ThreadPoolExecutor? = null

    private fun prepare() {
        if (ThreadPoolServiceImpl.JAVA_THREAD == IIConfig["ThreadPoolServiceImpl"]) {
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
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun newTask(runnable: () -> Unit) {
        if (ThreadPoolServiceImpl.JAVA_THREAD == IIConfig["ThreadPoolServiceImpl"]) {
            if (EXECUTOR_SERVICE == null) {
                prepare()
            }
            EXECUTOR_SERVICE?.execute(runnable)
        } else {
            GlobalScope.launch(Dispatchers.IO) {
                runnable()
            }
        }
    }

    override fun newTask(runnable: Runnable) {
        newTask {
            runnable.run()
        }
    }

    override fun terminate() {
        "Terminating the execute service".logInfo()
        EXECUTOR_SERVICE?.shutdownNow()
        EXECUTOR_SERVICE = null
    }

}