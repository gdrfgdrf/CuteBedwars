package io.github.gdrfgdrf.cutebedwars.abstracts.core

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("disabler")
interface IDisabler {
    fun reloadPhase()

    companion object {
        fun get(): IDisabler = Mediator.get(IDisabler::class.java)!!
    }
}