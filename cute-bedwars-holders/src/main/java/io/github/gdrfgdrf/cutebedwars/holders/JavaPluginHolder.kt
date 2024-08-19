package io.github.gdrfgdrf.cutebedwars.holders

import io.github.gdrfgdrf.cutebedwars.holders.base.Holder
import org.bukkit.plugin.java.JavaPlugin

fun javaPluginHolder(): JavaPluginHolder = JavaPluginHolder

object JavaPluginHolder : Holder() {
    const val JAVA_PLUGIN_NAME = "cute-bedwars-java-plugin"

    fun set(javaPlugin: JavaPlugin) {
        set(JAVA_PLUGIN_NAME, javaPlugin)
    }

    override fun set(name: String, any: Any) {
        map[name] = any
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> get(name: String): T {
        return map[name] as T
    }

    fun get(): JavaPlugin {
        return get(JAVA_PLUGIN_NAME)
    }
}