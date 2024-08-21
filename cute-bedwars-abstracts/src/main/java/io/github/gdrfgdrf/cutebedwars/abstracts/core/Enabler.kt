package io.github.gdrfgdrf.cutebedwars.abstracts.core

import io.github.gdrfgdrf.cutebedwars.abstracts.base.AbstractContent
import io.github.gdrfgdrf.cutebedwars.abstracts.manager.AbstractManager

abstract class Enabler : AbstractContent(Enabler::class.java) {
    abstract fun reloadPhase()

    companion object {
        fun get(): Enabler = AbstractManager.get(Enabler::class.java)
    }
}