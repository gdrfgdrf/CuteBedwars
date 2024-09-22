package io.github.gdrfgdrf.cutebedwars.abstracts.editing

import io.github.gdrfgdrf.cutebedwars.abstracts.editing.change.AbstractChange
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.change.IChangeClassHolder
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("change_type_registry")
@KotlinSingleton
interface IChangeTypeRegistry {
    fun init()
    fun clear()

    fun register(name: String, abstractChangeClass: Class<AbstractChange<*>>)
    fun get(name: String): IChangeClassHolder<AbstractChange<*>>?
    fun forEach(block: (String, IChangeClassHolder<AbstractChange<*>>) -> Unit)

    companion object {
        fun get(): IChangeTypeRegistry = Mediator.get(IChangeTypeRegistry::class.java)!!
    }

}