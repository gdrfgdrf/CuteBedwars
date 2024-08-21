package io.github.gdrfgdrf.cutebedwars.abstracts.commands

import io.github.gdrfgdrf.cutebedwars.abstracts.base.AbstractContent
import io.github.gdrfgdrf.cutebedwars.abstracts.manager.AbstractManager

abstract class CommandRegistry : AbstractContent(CommandRegistry::class.java) {
    abstract fun registerCommands()

    companion object {
        fun get(): CommandRegistry = AbstractManager.get(CommandRegistry::class.java)
    }
}