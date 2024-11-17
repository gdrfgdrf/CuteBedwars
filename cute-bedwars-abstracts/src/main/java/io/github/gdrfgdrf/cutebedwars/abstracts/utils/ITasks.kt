package io.github.gdrfgdrf.cutebedwars.abstracts.utils

import io.github.gdrfgdrf.cutebedwars.abstracts.frequencytasks.IFrequencyTask
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import java.util.concurrent.TimeUnit

@Service("tasks")
@KotlinSingleton
interface ITasks {
    fun asyncTask(runnable:() -> Unit)
    fun <T> syncTask(lock: Any, supplier: () -> T?)

    fun frequencyTask(frequency: Long, function: (IFrequencyTask) -> Unit): IStopSignal
    fun frequencyTask(frequency: Long, timeUnit: TimeUnit, function: (IFrequencyTask) -> Unit): IStopSignal

    companion object {
        fun instance(): ITasks = Mediator.get(ITasks::class.java)!!
    }
}