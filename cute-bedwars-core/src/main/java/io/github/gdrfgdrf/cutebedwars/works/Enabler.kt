package io.github.gdrfgdrf.cutebedwars.works

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.ICommandRegistry
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.ISubCommandManager
import io.github.gdrfgdrf.cutebedwars.abstracts.core.IEnabler
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPluginState
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import org.bukkit.plugin.java.JavaPlugin

@ServiceImpl("enabler")
object Enabler : IEnabler {
    fun enable(javaPlugin: JavaPlugin) {
        javaPlugin.logger.info("------------------------ CuteBedwars Enable Phase ------------------------")

        runCatching {
            ICommandRegistry.instance().registerCommands()
            Plugin.state = IPluginState.valueOf("RUNNING")
        }.onFailure {
            javaPlugin.logger.severe("An error occurred while enabling CuteBedwars")
        }

        javaPlugin.logger.info("------------------------ CuteBedwars Enable Phase ------------------------")
    }

    override fun reloadPhase() {
        "Start reloading (Enabler)".logInfo()
        ISubCommandManager.instance().scanAndRegister()
    }
}