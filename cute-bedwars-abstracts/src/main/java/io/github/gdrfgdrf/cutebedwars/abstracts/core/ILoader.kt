package io.github.gdrfgdrf.cutebedwars.abstracts.core

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("loader")
interface ILoader {
    fun reloadPhase()

    companion object {
        fun get(): ILoader = Mediator.get(ILoader::class.java)!!
    }
}