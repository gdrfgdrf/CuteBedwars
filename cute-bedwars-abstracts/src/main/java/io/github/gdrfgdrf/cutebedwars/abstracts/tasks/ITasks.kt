package io.github.gdrfgdrf.cutebedwars.abstracts.tasks

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("tasks")
@KotlinSingleton
interface ITasks {
    fun start()
    fun terminate()
    fun <T> add(taskEntry: ITaskEntry<T>)

    companion object {
        fun instance(): ITasks = Mediator.get(ITasks::class.java)!!
    }
}