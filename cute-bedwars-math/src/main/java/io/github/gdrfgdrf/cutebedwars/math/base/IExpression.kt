package io.github.gdrfgdrf.cutebedwars.math.base

import io.github.gdrfgdrf.cutebedwars.math.common.Arguments
import io.github.gdrfgdrf.cutebedwars.math.common.MathNumber

interface IExpression {
    fun calculate(arguments: Arguments): MathNumber
}