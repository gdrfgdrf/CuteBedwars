package io.github.gdrfgdrf.cutebedwars.abstracts.core

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPluginState
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import org.bukkit.plugin.java.JavaPlugin

@Service("plugin")
@KotlinSingleton
interface IPlugin {
    fun state(): IPluginState?
    fun state(state: IPluginState)
    fun javaPlugin(): JavaPlugin?

    companion object {
        fun instance(): IPlugin = Mediator.get(IPlugin::class.java)!!
    }
}