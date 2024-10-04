package io.github.gdrfgdrf.cutebedwars.abstracts.utils

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("tasks")
@KotlinSingleton
interface ITasks {
    fun runAsyncTask(runnable:() -> Unit)
    fun <T> runSyncTask(lock: Any, supplier: () -> T?)

    companion object {
        fun instance(): ITasks = Mediator.get(ITasks::class.java)!!
    }
}