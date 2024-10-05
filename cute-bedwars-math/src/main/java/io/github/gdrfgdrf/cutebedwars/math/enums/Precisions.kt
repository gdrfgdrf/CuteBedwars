package io.github.gdrfgdrf.cutebedwars.math.enums

import io.github.gdrfgdrf.cutebedwars.math.common.MathNumber

enum class Precisions(val digit: Int, val step: Steps, val pattern: String) {
    TWO(2, Steps.ONE, "#.00")

    ;



    fun next(mathNumber: MathNumber): MathNumber {
        return mathNumber.cut(this).plus(step.mathNumber)
    }
}