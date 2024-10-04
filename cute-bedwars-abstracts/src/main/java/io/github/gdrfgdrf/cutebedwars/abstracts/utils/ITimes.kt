package io.github.gdrfgdrf.cutebedwars.abstracts.utils

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("times")
@KotlinSingleton
interface ITimes {
    fun now(): String

    companion object {
        fun instance(): ITimes = Mediator.get(ITimes::class.java)!!
    }
}