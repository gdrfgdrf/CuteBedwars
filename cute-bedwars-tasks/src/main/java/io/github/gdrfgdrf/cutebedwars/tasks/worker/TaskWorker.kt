package io.github.gdrfgdrf.cutebedwars.tasks.worker

import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IThreadPoolService
import io.github.gdrfgdrf.cutebedwars.tasks.TaskManager
import io.github.gdrfgdrf.cutebedwars.tasks.entry.FutureTaskEntry
import io.github.gdrfgdrf.cutebedwars.utils.extension.logInfo
import io.github.gdrfgdrf.cutebedwars.utils.extension.sleepSafely
import io.github.gdrfgdrf.cutebedwars.utils.thread.ThreadPoolService
import java.util.concurrent.LinkedBlockingQueue

object TaskWorker : Runnable {
    private val threadPoolService = ThreadPoolService

    override fun run() {
        "Task worker started".logInfo()

        while (!TaskManager.isTerminated()) {
            val taskEntry = TaskManager.TASK_ENTRY_QUEUE.poll() ?: continue

            if (taskEntry.customLock() == null) {
                threadPoolService.newTask {
                    val result = taskEntry.supplier()()

                    if (taskEntry is FutureTaskEntry) {
                        taskEntry.result(result)
                    } else {
                        taskEntry.notifyMethodFinished()
                    }
                }
            } else {
                val taskEntries = TaskManager.SYNCHRONIZED_TASK_ENTRY.computeIfAbsent(taskEntry.customLock()!!) {
                    LinkedBlockingQueue()
                }

                taskEntries.put(taskEntry)
            }

            sleepSafely(100)
        }

        "Task worker terminated".logInfo()
    }
}