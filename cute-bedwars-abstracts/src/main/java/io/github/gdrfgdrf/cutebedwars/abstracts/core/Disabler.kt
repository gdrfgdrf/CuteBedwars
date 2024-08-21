package io.github.gdrfgdrf.cutebedwars.abstracts.core

import io.github.gdrfgdrf.cutebedwars.abstracts.base.AbstractContent
import io.github.gdrfgdrf.cutebedwars.abstracts.manager.AbstractManager

abstract class Disabler : AbstractContent(Disabler::class.java) {
    abstract fun reloadPhase()

    companion object {
        fun get(): Disabler = AbstractManager.get(Disabler::class.java)
    }
}