package io.github.gdrfgdrf.cutebedwars.abstracts.commands

import io.github.gdrfgdrf.cutebedwars.abstracts.base.AbstractContent
import io.github.gdrfgdrf.cutebedwars.abstracts.manager.AbstractManager

abstract class SubCommandManager : AbstractContent(SubCommandManager::class.java) {
    abstract fun clear()
    abstract fun scanAndRegister()

    companion object {
        fun get(): SubCommandManager = AbstractManager.get(SubCommandManager::class.java)
    }
}