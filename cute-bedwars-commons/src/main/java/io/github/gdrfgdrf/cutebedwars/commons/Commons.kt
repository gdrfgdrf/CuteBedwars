package io.github.gdrfgdrf.cutebedwars.commons

import io.github.gdrfgdrf.cutebedwars.holders.javaPluginHolder
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Logger

fun logger(): Logger {
    return (javaPluginHolder().get(javaPluginHolder().JAVA_PLUGIN_NAME) as JavaPlugin).logger
}