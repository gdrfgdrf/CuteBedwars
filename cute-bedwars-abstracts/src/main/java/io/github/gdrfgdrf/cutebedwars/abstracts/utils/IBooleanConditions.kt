package io.github.gdrfgdrf.cutebedwars.abstracts.utils

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("boolean_conditions")
@KotlinSingleton
interface IBooleanConditions {
    fun onlyNumber(any: Any): Boolean

    companion object {
        fun instance(): IBooleanConditions = Mediator.get(IBooleanConditions::class.java)!!
    }
}