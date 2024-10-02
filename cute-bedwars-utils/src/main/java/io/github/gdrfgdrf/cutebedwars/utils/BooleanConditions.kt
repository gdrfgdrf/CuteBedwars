package io.github.gdrfgdrf.cutebedwars.utils

import io.github.gdrfgdrf.cutebedwars.utils.extension.isInt
import io.github.gdrfgdrf.cutebedwars.utils.extension.isLong

object BooleanConditions {

    fun onlyNumber(any: Any): Boolean {
        if (any is Number) {
            return true
        }

        return any.toString().isInt() || any.toString().isLong()
    }

}