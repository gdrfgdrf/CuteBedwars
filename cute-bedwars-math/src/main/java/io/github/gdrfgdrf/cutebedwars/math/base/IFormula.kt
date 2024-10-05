package io.github.gdrfgdrf.cutebedwars.math.base

import io.github.gdrfgdrf.cutebedwars.math.common.Argument
import io.github.gdrfgdrf.cutebedwars.math.common.MathNumber

interface IFormula {
    fun calculate(vararg argument: Argument): MathNumber
}