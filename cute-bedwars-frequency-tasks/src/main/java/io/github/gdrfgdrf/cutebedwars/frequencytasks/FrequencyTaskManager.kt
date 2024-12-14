package io.github.gdrfgdrf.cutebedwars.frequencytasks

import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IThreadPoolService
import io.github.gdrfgdrf.cutebedwars.abstracts.frequencytasks.IFrequencyTask
import io.github.gdrfgdrf.cutebedwars.abstracts.frequencytasks.IFrequencyTaskManager
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IStopSignal
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.asyncTask
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.sleepSafely
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import java.util.concurrent.CopyOnWriteArrayList

@ServiceImpl("frequency_task_manager")
object FrequencyTaskManager : IFrequencyTaskManager {
    private val list = CopyOnWriteArrayList<InternalTaskEntry>()
    private var terminated = false

    override fun start() {
        "Starting frequency task manager".logInfo()
        asyncTask {
            while (!terminated) {
                run()
                sleepSafely(10)
            }

            "Frequency task manager is terminated".logInfo()
        }
    }

    override fun terminate() {
        "Terminating frequency task manager".logInfo()
        list.clear()
        terminated = true
    }

    private fun run() {
        list.forEach { taskEntry ->
            if (taskEntry.stopSignal.stopped) {
                list.remove(taskEntry)
                return@forEach
            }
            if (taskEntry.task.canceled) {
                list.remove(taskEntry)
                return@forEach
            }
            if (taskEntry.task.canRun()) {
                IThreadPoolService.instance().newTask {
                    taskEntry.task.run()
                }
            }
        }
    }

    override fun add(frequencyTask: IFrequencyTask): IStopSignal {
        val taskEntry = InternalTaskEntry.create(frequencyTask)
        this.list.add(taskEntry)
        return taskEntry.stopSignal
    }

    override fun remove(frequencyTask: IFrequencyTask) {
        list.stream()
            .filter {
                return@filter it.task == frequencyTask
            }.forEach {
                list.remove(it)
            }
    }

    class InternalTaskEntry private constructor(
        val task: IFrequencyTask,
        val stopSignal: IStopSignal
    ) {
        companion object {
            fun create(frequencyTask: IFrequencyTask, stopSignal: IStopSignal) =
                InternalTaskEntry(frequencyTask, stopSignal)

            fun create(frequencyTask: IFrequencyTask): InternalTaskEntry {
                val stopSignal = IStopSignal.new()
                return create(frequencyTask, stopSignal)
            }
        }
    }
}