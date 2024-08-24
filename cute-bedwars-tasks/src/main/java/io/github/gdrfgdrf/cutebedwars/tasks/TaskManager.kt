package io.github.gdrfgdrf.cutebedwars.tasks

import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IThreadPoolService
import io.github.gdrfgdrf.cutebedwars.tasks.entry.TaskEntry
import io.github.gdrfgdrf.cutebedwars.tasks.worker.SyncTaskWorker
import io.github.gdrfgdrf.cutebedwars.tasks.worker.TaskWorker
import io.github.gdrfgdrf.cutebedwars.utils.extension.logInfo
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.LinkedBlockingQueue

object TaskManager {
    val TASK_ENTRY_QUEUE = ConcurrentLinkedQueue<TaskEntry<*>>()
    val SYNCHRONIZED_TASK_ENTRY = ConcurrentHashMap<Any, LinkedBlockingQueue<TaskEntry<*>>>()
    private val TASK_WORKER = TaskWorker
    private val SYNCHRONIZED_TASK_WORKER = SyncTaskWorker

    private var terminated = false

    fun start() {
        "Starting task manager".logInfo()

        terminated = false

        val threadPoolService = IThreadPoolService.get()
        threadPoolService.newTask(TASK_WORKER)
        threadPoolService.newTask(SYNCHRONIZED_TASK_WORKER)
    }

    fun isTerminated(): Boolean = terminated

    fun terminate() {
        terminated = true
        TASK_ENTRY_QUEUE.clear()
        SYNCHRONIZED_TASK_ENTRY.clear()
        "Task manager terminated".logInfo()
    }

    fun add(taskEntry: TaskEntry<*>) {
        if (terminated) {
            throw IllegalStateException("Task manager has been terminated")
        }
        TASK_ENTRY_QUEUE.offer(taskEntry)
    }




}