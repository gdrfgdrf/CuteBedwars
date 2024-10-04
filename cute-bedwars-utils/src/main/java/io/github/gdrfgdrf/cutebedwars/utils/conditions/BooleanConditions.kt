package io.github.gdrfgdrf.cutebedwars.utils.conditions

import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IBooleanConditions
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.isInt
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.isLong
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl

@ServiceImpl("boolean_conditions")
object BooleanConditions : IBooleanConditions {
    override fun onlyNumber(any: Any): Boolean {
        if (any is Number) {
            return true
        }

        return any.toString().isInt() || any.toString().isLong()
    }
}