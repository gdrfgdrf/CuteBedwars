package io.github.gdrfgdrf.cutebedwars.abstracts.commands

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("subcommand_manager")
@KotlinSingleton
interface ISubCommandManager {
    fun clear()
    fun scanAndRegister()

    companion object {
        fun get(): ISubCommandManager = Mediator.get(ISubCommandManager::class.java)!!
    }
}