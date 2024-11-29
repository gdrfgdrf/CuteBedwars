package io.github.gdrfgdrf.cutebedwars.abstracts.commons

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("constants")
@KotlinSingleton
interface IConstants {
    fun <T> get(key: String): T

    companion object {
        fun instance(): IConstants = Mediator.get(IConstants::class.java)!!
        operator fun get(key: String): String = instance().get(key)
    }
}