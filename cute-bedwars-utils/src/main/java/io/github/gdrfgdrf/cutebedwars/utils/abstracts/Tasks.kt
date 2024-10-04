package io.github.gdrfgdrf.cutebedwars.utils.abstracts

import io.github.gdrfgdrf.cutebedwars.abstracts.tasks.IFutureTaskEntry
import io.github.gdrfgdrf.cutebedwars.abstracts.tasks.ITaskEntry
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.ITasks
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl

@ServiceImpl("tasks")
@KotlinSingleton
object Tasks : ITasks {
    override fun runAsyncTask(runnable: () -> Unit) {
        ITaskEntry.new<Any>(runnable)
            .run()
    }

    override fun <T> runSyncTask(lock: Any, supplier: () -> T?) {
        IFutureTaskEntry.new(supplier)
            .customLock(lock)
            .run()
    }
}