package io.github.gdrfgdrf.cutebedwars.abstracts.commands

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("command_registry")
@KotlinSingleton
interface CommandRegistry {
    fun registerCommands()

    companion object {
        fun get(): CommandRegistry = Mediator.get(CommandRegistry::class.java)!!
    }
}