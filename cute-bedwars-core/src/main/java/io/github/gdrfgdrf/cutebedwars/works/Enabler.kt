package io.github.gdrfgdrf.cutebedwars.works

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.CommandRegistry
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.SubCommandManager
import io.github.gdrfgdrf.cutebedwars.abstracts.core.Enabler
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.PluginState

object Enabler : Enabler() {
    fun enable() {
        CommandRegistry.get().registerCommands()

        Plugin.state = PluginState.RUNNING
    }

    override fun reloadPhase() {
        SubCommandManager.get().scanAndRegister()
    }
}