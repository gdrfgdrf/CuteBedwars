package io.github.gdrfgdrf.cutebedwars.works

import io.github.gdrfgdrf.cutebedwars.abstracts.core.IPlugin
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPluginState
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import org.bukkit.NamespacedKey
import org.bukkit.plugin.java.JavaPlugin

@ServiceImpl("plugin")
object Plugin : IPlugin {
    override var javaPlugin: JavaPlugin? = null
    override var state: IPluginState? = null
        set(value) {
            "Plugin state is changed from $field to $value".logInfo()
            field = value
        }

    private var namespacedKey: NamespacedKey? = null

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