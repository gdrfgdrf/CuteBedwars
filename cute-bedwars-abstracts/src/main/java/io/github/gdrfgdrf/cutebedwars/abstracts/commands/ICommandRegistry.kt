package io.github.gdrfgdrf.cutebedwars.abstracts.commands

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("command_registry")
@KotlinSingleton
interface ICommandRegistry {
    fun registerCommands()

    companion object {
        fun instance(): ICommandRegistry = Mediator.get(ICommandRegistry::class.java)!!
    }
}