package io.github.gdrfgdrf.cutebedwars.abstracts.core

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPluginState
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("plugin")
@KotlinSingleton
interface IPlugin {
    fun state(): IPluginState?
    fun state(state: IPluginState)

    companion object {
        fun get(): IPlugin = Mediator.get(IPlugin::class.java)!!
    }
}