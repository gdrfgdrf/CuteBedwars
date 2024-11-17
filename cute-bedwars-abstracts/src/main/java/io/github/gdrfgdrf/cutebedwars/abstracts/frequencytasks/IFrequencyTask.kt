package io.github.gdrfgdrf.cutebedwars.abstracts.frequencytasks

import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IStopSignal
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import java.util.concurrent.TimeUnit

@Service("frequency_task", singleton = false)
interface IFrequencyTask {
    var frequency: Long
    var frequencyUnit: TimeUnit
    var canceled: Boolean
    val function: (IFrequencyTask) -> Unit

    fun count(): Int
    fun lastRun(): Long

    fun run()
    fun canRun(): Boolean

    fun add(): IStopSignal

    companion object {
        fun new(
            frequency: Long,
            frequencyUnit: TimeUnit,
            function: (IFrequencyTask) -> Unit
        ): IFrequencyTask = Mediator.get(
            IFrequencyTask::class.java,
            ArgumentSet(
                arrayOf(
                    frequency,
                    frequencyUnit,
                    function
                )
            )
        )!!
    }
}