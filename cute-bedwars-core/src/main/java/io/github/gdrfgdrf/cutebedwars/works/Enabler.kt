package io.github.gdrfgdrf.cutebedwars.works

import io.github.gdrfgdrf.cutebedwars.command.registry.CommandRegistry

object Enabler {
    fun enable() {
        CommandRegistry.register()
    }
}