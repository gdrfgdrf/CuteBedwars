package io.github.gdrfgdrf.cutebedwars.abstracts.core

import io.github.gdrfgdrf.cutebedwars.abstracts.base.AbstractContent
import io.github.gdrfgdrf.cutebedwars.abstracts.manager.AbstractManager

abstract class Loader : AbstractContent(Loader::class.java) {
    abstract fun reloadPhase()

    companion object {
        fun get(): Loader = AbstractManager.get(Loader::class.java)
    }
}