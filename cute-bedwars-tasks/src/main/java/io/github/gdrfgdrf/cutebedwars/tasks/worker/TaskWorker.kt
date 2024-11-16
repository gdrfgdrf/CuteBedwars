package io.github.gdrfgdrf.cutebedwars.tasks.worker

import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IThreadPoolService
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.sleepSafely
import io.github.gdrfgdrf.cutebedwars.tasks.Tasks
import io.github.gdrfgdrf.cutebedwars.tasks.entry.FutureTaskEntry
import java.util.concurrent.LinkedBlockingQueue

object TaskWorker : Runnable {
    private val threadPoolService = IThreadPoolService.instance()

    override fun run() {
        "Task worker started".logInfo()

        while (!Tasks.isTerminated()) {
            val taskEntry = Tasks.TASK_ENTRY_QUEUE.poll() ?: continue

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
                val taskEntries = Tasks.SYNCHRONIZED_TASK_ENTRY.computeIfAbsent(taskEntry.customLock()!!) {
                    LinkedBlockingQueue()
                }

                taskEntries.put(taskEntry)
            }

            if (!Tasks.isTerminated()) {
                sleepSafely(100)
            } else {
                break
            }
        }

        "Task worker terminated".logInfo()
    }
}