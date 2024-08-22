package io.github.gdrfgdrf.cutebedwars.abstracts.enums

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumService
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@EnumService("plugin_state_enum")
interface IPluginState {
    fun name_(): String

    companion object {
        fun get(name: String): IPluginState = Mediator.get(IPluginState::class.java, ArgumentSet(arrayOf(name)))!!
    }
}