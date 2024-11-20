package io.github.gdrfgdrf.cutebedwars.frequencytasks

import io.github.gdrfgdrf.cutebedwars.abstracts.frequencytasks.IFrequencyTask
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IStopSignal
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import java.util.concurrent.TimeUnit

@ServiceImpl("frequency_task", needArgument = true)
class FrequencyTask(
    override var frequency: Long,
    override var frequencyUnit: TimeUnit,
    override val function: (IFrequencyTask) -> Unit,
) : IFrequencyTask {
    @Suppress("UNCHECKED_CAST")
    constructor(argumentSet: ArgumentSet): this(
        argumentSet.args[0] as Long,
        argumentSet.args[1] as TimeUnit,
        argumentSet.args[2] as (IFrequencyTask) -> Unit,
    )

    override var canceled: Boolean = false
    private var count: Int = 0
    private var lastRun: Long = 0

    override fun count(): Int = count
    override fun lastRun(): Long = lastRun

    override fun run(): Boolean {
        if (canceled) {
            return false
        }
        if (lastRun == 0L) {
            lastRun = System.currentTimeMillis()
            function(this)
            count++
            return true
        }

        val currentTime = System.currentTimeMillis()
        val convertedFrequency = frequencyUnit.convert(frequency, TimeUnit.MILLISECONDS)
        if (currentTime - lastRun >= convertedFrequency) {
            lastRun = currentTime
            function(this)
            count++
            return true
        }
        return false
    }

    override fun canRun(): Boolean {
        if (canceled) {
            return false
        }
        if (lastRun == 0L) {
            return true
        }

        val currentTime = System.currentTimeMillis()
        val convertedFrequency = frequencyUnit.convert(frequency, TimeUnit.MILLISECONDS)
        return currentTime - lastRun >= convertedFrequency
    }

    override fun add(): IStopSignal {
        return FrequencyTaskManager.add(this)
    }
}