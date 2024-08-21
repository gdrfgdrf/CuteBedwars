package io.github.gdrfgdrf.cutebedwars.works

import io.github.gdrfgdrf.cutebedwars.abstracts.core.Plugin
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.PluginState

object Plugin : Plugin() {
    var state: PluginState? = null

    override fun state(): PluginState? {
        return state
    }

    override fun state(state: PluginState) {
        this.state = state
    }
}