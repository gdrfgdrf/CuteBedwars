package io.github.gdrfgdrf.cutebedwars.works

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.ICommandRegistry
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.ISubCommandManager
import io.github.gdrfgdrf.cutebedwars.abstracts.core.IEnabler
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPluginState
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl

@ServiceImpl("enabler")
object Enabler : IEnabler {
    fun enable() {
        ICommandRegistry.instance().registerCommands()

        Plugin.state = IPluginState.valueOf("RUNNING")
    }

    override fun reloadPhase() {
        "Start reloading (Enabler)".logInfo()
        ISubCommandManager.instance().scanAndRegister()
    }
}