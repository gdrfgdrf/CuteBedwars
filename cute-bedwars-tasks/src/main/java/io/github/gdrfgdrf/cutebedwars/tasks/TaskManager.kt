package io.github.gdrfgdrf.cutebedwars.tasks

import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IThreadPoolService
import io.github.gdrfgdrf.cutebedwars.abstracts.tasks.ITaskEntry
import io.github.gdrfgdrf.cutebedwars.abstracts.tasks.ITaskManager
import io.github.gdrfgdrf.cutebedwars.tasks.worker.SyncTaskWorker
import io.github.gdrfgdrf.cutebedwars.tasks.worker.TaskWorker
import io.github.gdrfgdrf.cutebedwars.utils.extension.logInfo
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.LinkedBlockingQueue

@ServiceImpl("task_manager")
object TaskManager : ITaskManager {
    val TASK_ENTRY_QUEUE = ConcurrentLinkedQueue<ITaskEntry<*>>()
    val SYNCHRONIZED_TASK_ENTRY = ConcurrentHashMap<Any, LinkedBlockingQueue<ITaskEntry<*>>>()
    private val TASK_WORKER = TaskWorker
    private val SYNCHRONIZED_TASK_WORKER = SyncTaskWorker

    private var terminated = false

    override fun start() {
        "Starting task manager".logInfo()

        terminated = false

        val threadPoolService = IThreadPoolService.get()
        threadPoolService.newTask(TASK_WORKER)
        threadPoolService.newTask(SYNCHRONIZED_TASK_WORKER)
    }

    fun isTerminated(): Boolean = terminated

    override fun terminate() {
        terminated = true
        TASK_ENTRY_QUEUE.clear()
        SYNCHRONIZED_TASK_ENTRY.clear()
        "Task manager terminated".logInfo()
    }

    override fun <T> add(taskEntry: ITaskEntry<T>) {
        if (terminated) {
            throw IllegalStateException("Task manager has been terminated")
        }
        TASK_ENTRY_QUEUE.offer(taskEntry)
    }




}