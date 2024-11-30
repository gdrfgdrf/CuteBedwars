package io.github.gdrfgdrf.cutebedwars.tasks.worker

import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IThreadPoolService
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logError
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.sleepSafely
import io.github.gdrfgdrf.cutebedwars.tasks.entry.FutureTaskEntry
import io.github.gdrfgdrf.cutebedwars.tasks.TaskManager

object SyncTaskWorker : Runnable {
    private val threadPoolService = IThreadPoolService.instance()

    override fun run() {
        "Synchronized task worker started".logInfo()

        while (!TaskManager.isTerminated()) {
            TaskManager.SYNCHRONIZED_TASK_ENTRY.forEach { (lock, taskEntries) ->
                TaskManager.SYNCHRONIZED_TASK_ENTRY.remove(lock)

                threadPoolService.newTask {
                    var nextRound = true

                    while (nextRound) {
                        runCatching {
                            val taskEntry = taskEntries.take()

                            if (lock is String) {
                                synchronized(lock.intern()) {
                                    val result = taskEntry.supplier()()

                                    if (taskEntry is FutureTaskEntry) {
                                        taskEntry.result(result)
                                    } else {
                                        taskEntry.notifyMethodFinished()
                                    }
                                }
                            } else {
                                synchronized(lock) {
                                    val result = taskEntry.supplier()

                                    if (taskEntry is FutureTaskEntry) {
                                        taskEntry.result(result)
                                    } else {
                                        taskEntry.notifyMethodFinished()
                                    }
                                }
                            }
                        }.onFailure {
                            "InterruptedException when taking out task from linked blocking queue".logError(it)
                        }

                        if (taskEntries.isEmpty()) {
                            nextRound = false
                        }
                    }
                }
            }

            if (!TaskManager.isTerminated()) {
                sleepSafely(50)
            } else {
                break
            }
        }

        "Synchronized task worker terminated".logInfo()
    }
}