package io.github.gdrfgdrf.cutebedwars.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPluginState
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumServiceImpl

@EnumServiceImpl("plugin_state_enum")
enum class PluginState : IPluginState {
    LOADING,
    RUNNING;

    override fun name_(): String {
        return name
    }
}