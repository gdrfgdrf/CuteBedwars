package io.github.gdrfgdrf.cutebedwars.works

import io.github.gdrfgdrf.cutebedwars.abstracts.core.IPlugin
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPluginState
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl

@ServiceImpl("plugin")
object Plugin : IPlugin {
    var state: IPluginState? = null

    override fun state(): IPluginState? {
        return state
    }

    override fun state(state: IPluginState) {
        this.state = state
    }
}