package io.github.gdrfgdrf.cutebedwars.utils.abstracts

import io.github.gdrfgdrf.cutebedwars.abstracts.frequencytasks.IFrequencyTask
import io.github.gdrfgdrf.cutebedwars.abstracts.tasks.IFutureTaskEntry
import io.github.gdrfgdrf.cutebedwars.abstracts.tasks.ITaskEntry
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.ITasks
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import java.util.concurrent.TimeUnit

@ServiceImpl("tasks")
@KotlinSingleton
object Tasks : ITasks {
    override fun asyncTask(runnable: () -> Unit) {
        ITaskEntry.new<Any>(runnable)
            .run()
    }

    override fun <T> syncTask(lock: Any, supplier: () -> T?) {
        IFutureTaskEntry.new(supplier)
            .customLock(lock)
            .run()
    }

    override fun frequencyTask(frequency: Long, function: (IFrequencyTask) -> Unit) {
        IFrequencyTask.new(frequency, TimeUnit.MILLISECONDS, function)
            .add()
    }

    override fun frequencyTask(frequency: Long, timeUnit: TimeUnit, function: (IFrequencyTask) -> Unit) {
        IFrequencyTask.new(frequency, timeUnit, function)
            .add()
   }
}