package io.github.gdrfgdrf.cutebedwars.works

import io.github.gdrfgdrf.cutebedwars.commands.registry.CommandRegistry

object Enabler {
    fun enable() {
        CommandRegistry.register()
    }
}