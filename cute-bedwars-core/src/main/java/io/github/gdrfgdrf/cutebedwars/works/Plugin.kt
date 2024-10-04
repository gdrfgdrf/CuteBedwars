package io.github.gdrfgdrf.cutebedwars.works

import io.github.gdrfgdrf.cutebedwars.abstracts.core.IPlugin
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPluginState
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import org.bukkit.plugin.java.JavaPlugin

@ServiceImpl("plugin")
object Plugin : IPlugin {
    var javaPlugin: JavaPlugin? = null
    var state: IPluginState? = null

    override fun state(): IPluginState? {
        return state
    }

    override fun state(state: IPluginState) {
        "Plugin state is changed from ${this.state} to $state".logInfo()
        this.state = state
    }

    override fun javaPlugin(): JavaPlugin? {
        return javaPlugin
    }
}