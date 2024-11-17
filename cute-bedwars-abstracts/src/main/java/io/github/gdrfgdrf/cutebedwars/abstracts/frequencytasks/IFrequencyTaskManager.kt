package io.github.gdrfgdrf.cutebedwars.abstracts.frequencytasks

import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IStopSignal
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("frequency_task_manager")
@KotlinSingleton
interface IFrequencyTaskManager {
    fun start()
    fun terminate()

    fun add(frequencyTask: IFrequencyTask): IStopSignal
    fun remove(frequencyTask: IFrequencyTask)
}