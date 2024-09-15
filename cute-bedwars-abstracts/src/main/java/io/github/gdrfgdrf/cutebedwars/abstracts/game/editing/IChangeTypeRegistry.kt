package io.github.gdrfgdrf.cutebedwars.abstracts.game.editing

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("change_type_registry")
@KotlinSingleton
interface IChangeTypeRegistry {
    fun init()
    fun clear()

    fun register(name: String, abstractChangeClass: Class<AbstractChange<*>>)
    fun get(name: String): Class<AbstractChange<*>>?
    fun forEach(block: (String, Class<AbstractChange<*>>) -> Unit)

    companion object {
        fun get(): IChangeTypeRegistry = Mediator.get(IChangeTypeRegistry::class.java)!!
    }

}