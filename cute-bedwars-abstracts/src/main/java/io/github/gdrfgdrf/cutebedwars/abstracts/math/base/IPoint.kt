package io.github.gdrfgdrf.cutebedwars.abstracts.math.base

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber

interface IPoint {
    val step: Int
    val numbers: Array<IMathNumber>

    fun isValid(): Boolean {
        numbers.forEach { number ->
            if (number.toDouble().isNaN()) {
                return false
            }
        }
        return true
    }
}