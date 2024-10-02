package io.github.gdrfgdrf.cutebedwars.abstracts.core

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("loader")
@KotlinSingleton
interface ILoader {
    fun reloadPhase()

    companion object {
        fun instance(): ILoader = Mediator.get(ILoader::class.java)!!
    }
}