package io.github.gdrfgdrf.cutebedwars.works

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.ICommandRegistry
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.ISubCommandManager
import io.github.gdrfgdrf.cutebedwars.abstracts.core.IEnabler
import io.github.gdrfgdrf.cutebedwars.abstracts.core.IPlugin
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPluginState
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.cuteframework.bean.BeanManager
import io.github.gdrfgdrf.cuteframework.utils.ClassUtils
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import java.util.LinkedHashSet

@ServiceImpl("enabler")
object Enabler : IEnabler {
    fun enable(javaPlugin: JavaPlugin) {
        javaPlugin.logger.info("------------------------ CuteBedwars Enable Phase ------------------------")

        runCatching {
            ICommandRegistry.instance().registerCommands()
            Plugin.state(IPluginState.valueOf("RUNNING"))

            enableEventListeners()
        }.onFailure {
            javaPlugin.logger.severe("An error occurred while enabling CuteBedwars")
        }

        javaPlugin.logger.info("------------------------ CuteBedwars Enable Phase ------------------------")
    }

    override fun reloadPhase() {
        "------------------------ CuteBedwars Reloading Phase (Enabler) ------------------------".logInfo()

        "Start reloading (Enabler)".logInfo()
        ISubCommandManager.instance().scanAndRegister()

        "------------------------ CuteBedwars Reloading Phase (Enabler) ------------------------".logInfo()
    }

    private fun enableEventListeners() {
        val javaPlugin = IPlugin.instance().javaPlugin() ?: throw IllegalStateException("java plugin is required")

        val classes = LinkedHashSet<Class<*>>()
        ClassUtils.searchJar(Enabler::class.java.classLoader, "io.github.gdrfgdrf.cutebedwars.events.listener", {
            return@searchJar it.interfaces.contains(Listener::class.java)
        }, classes)

        classes.forEach { listenerClass ->
            "Registering a event listener: $listenerClass".logInfo()
            val listener = BeanManager.getInstance().getBean(listenerClass.simpleName)

            Bukkit.getServer().pluginManager.registerEvents(listener as Listener, javaPlugin)
        }
    }
}