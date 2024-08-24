package io.github.gdrfgdrf.cutebedwars.abstracts.enums

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumService

@EnumService("plugin_state_enum")
interface IPluginState {
    fun name_(): String

    companion object {
        fun valueOf(name: String): IPluginState = Mediator.valueOf(IPluginState::class.java, name)!!
    }
}