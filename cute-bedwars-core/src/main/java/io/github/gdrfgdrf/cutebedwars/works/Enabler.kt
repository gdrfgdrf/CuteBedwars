package io.github.gdrfgdrf.cutebedwars.works

import de.tr7zw.changeme.nbtapi.NBT
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.ICommandRegistry
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.ISubCommandManager
import io.github.gdrfgdrf.cutebedwars.abstracts.core.IEnabler
import io.github.gdrfgdrf.cutebedwars.abstracts.core.IPlugin
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPluginState
import io.github.gdrfgdrf.cutebedwars.abstracts.items.IItemCollections
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IClasses
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logDebug
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logError
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

@ServiceImpl("enabler")
object Enabler : IEnabler {
    fun enable(javaPlugin: JavaPlugin) {
        javaPlugin.logger.info("------------------------ CuteBedwars Enable Phase ------------------------")

        runCatching {
            ICommandRegistry.instance().registerCommands()
            Plugin.state = IPluginState.valueOf("RUNNING")

            enableNbtApi()
            enableEventListeners()
            startPlayerInventoryScanning()
        }.onFailure {
            javaPlugin.logger.severe("An error occurred while enabling CuteBedwars")
        }

        javaPlugin.logger.info("------------------------ CuteBedwars Enable Phase ------------------------")
    }

    override fun reloadPhase() {
        "------------------------ CuteBedwars Reloading Phase (Enabler) ------------------------".logInfo()

        "Start reloading (Enabler)".logInfo()
        ISubCommandManager.instance().scanAndRegister()
        startPlayerInventoryScanning()

        "------------------------ CuteBedwars Reloading Phase (Enabler) ------------------------".logInfo()
    }

    private fun enableNbtApi() {
        if (!NBT.preloadApi()) {
            "NBT-API wasn't initialized properly".logError()
        }
    }

    private fun enableEventListeners() {
        val javaPlugin = IPlugin.instance().javaPlugin ?: throw IllegalStateException("java plugin is required")

        val classes = LinkedHashSet<Class<*>>()
        IClasses.instance().search(
            Enabler::class.java.classLoader,
            "io.github.gdrfgdrf.cutebedwars.events.listener",
            classes
        ) {
            return@search it.interfaces.contains(Listener::class.java)
        }

        classes.forEach { listenerClass ->
            "Registering an event listener: $listenerClass".logDebug()
            val listener = listenerClass.getDeclaredConstructor().newInstance()

            Bukkit.getServer().pluginManager.registerEvents(listener as Listener, javaPlugin)
        }
    }

    private fun startPlayerInventoryScanning() {
        IItemCollections.instance().startPlayerInventoryScanning()
    }
}