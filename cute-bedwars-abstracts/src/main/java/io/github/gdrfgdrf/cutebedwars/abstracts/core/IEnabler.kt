package io.github.gdrfgdrf.cutebedwars.abstracts.core

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("enabler")
@KotlinSingleton
interface IEnabler {
    fun reloadPhase()

    companion object {
        fun get(): IEnabler = Mediator.get(IEnabler::class.java)!!
    }
}