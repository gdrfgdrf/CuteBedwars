package io.github.gdrfgdrf.cutebedwars.works

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.ICommandRegistry
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.ISubCommandManager
import io.github.gdrfgdrf.cutebedwars.abstracts.core.IEnabler
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPluginState
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl

@ServiceImpl("enabler")
object Enabler : IEnabler {
    fun enable() {
        ICommandRegistry.get().registerCommands()

        Plugin.state = IPluginState.valueOf("RUNNING")
    }

    override fun reloadPhase() {
        ISubCommandManager.get().scanAndRegister()
    }
}