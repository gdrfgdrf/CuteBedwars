package io.github.gdrfgdrf.cutebedwars.abstracts.core

import io.github.gdrfgdrf.cutebedwars.abstracts.base.AbstractContent
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.PluginState
import io.github.gdrfgdrf.cutebedwars.abstracts.manager.AbstractManager

abstract class Plugin : AbstractContent(Plugin::class.java) {
    abstract fun state(): PluginState?
    abstract fun state(state: PluginState)

    companion object {
        fun get(): Plugin = AbstractManager.get(Plugin::class.java)
    }
}