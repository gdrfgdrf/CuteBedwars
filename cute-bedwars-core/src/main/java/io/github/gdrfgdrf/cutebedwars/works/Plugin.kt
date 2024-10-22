package io.github.gdrfgdrf.cutebedwars.works

import io.github.gdrfgdrf.cutebedwars.abstracts.core.IPlugin
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPluginState
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import org.bukkit.NamespacedKey
import org.bukkit.plugin.java.JavaPlugin

@ServiceImpl("plugin")
object Plugin : IPlugin {
    private var javaPlugin: JavaPlugin? = null
    private var state: IPluginState? = null

    private var namespacedKey: NamespacedKey? = null

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

    override fun javaPlugin(javaPlugin: JavaPlugin) {
        this.javaPlugin = javaPlugin
    }

    override fun namespacedKey(): NamespacedKey {
        if (namespacedKey == null) {
            if (javaPlugin == null) {
                throw IllegalStateException("java plugin is required")
            }
            namespacedKey = NamespacedKey(javaPlugin, "cutebedwars")
        }
        return namespacedKey!!
    }
}