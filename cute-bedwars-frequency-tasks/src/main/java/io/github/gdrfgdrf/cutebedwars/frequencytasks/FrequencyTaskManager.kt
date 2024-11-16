package io.github.gdrfgdrf.cutebedwars.frequencytasks

import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IThreadPoolService
import io.github.gdrfgdrf.cutebedwars.abstracts.frequencytasks.IFrequencyTask
import io.github.gdrfgdrf.cutebedwars.abstracts.frequencytasks.IFrequencyTaskManager
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.asyncTask
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.sleepSafely
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import java.util.concurrent.CopyOnWriteArrayList

@ServiceImpl("frequency_task_manager")
object FrequencyTaskManager : IFrequencyTaskManager {
    private val list = CopyOnWriteArrayList<IFrequencyTask>()
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
        terminated = true
    }

    private fun run() {
        list.forEach { frequencyTask ->
            if (frequencyTask.canceled) {
                list.remove(frequencyTask)
                return@forEach
            }
            if (frequencyTask.canRun()) {
                IThreadPoolService.instance().newTask {
                    frequencyTask.run()
                }
            }
        }
    }

    override fun add(frequencyTask: IFrequencyTask) {
        this.list.add(frequencyTask)
    }

    override fun remove(frequencyTask: IFrequencyTask) {
        list.remove(frequencyTask)
    }
}