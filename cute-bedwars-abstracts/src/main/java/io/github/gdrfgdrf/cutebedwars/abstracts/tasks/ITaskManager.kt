package io.github.gdrfgdrf.cutebedwars.abstracts.tasks

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("task_manager")
@KotlinSingleton
interface ITaskManager {
    fun start()
    fun terminate()
    fun <T> add(taskEntry: ITaskEntry<T>)

    companion object {
        fun get(): ITaskManager = Mediator.get(ITaskManager::class.java)!!
    }
}