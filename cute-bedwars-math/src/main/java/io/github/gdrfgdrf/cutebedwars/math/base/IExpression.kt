package io.github.gdrfgdrf.cutebedwars.math.base

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.math.common.Arguments

interface IExpression {
    fun calculate(arguments: Arguments): IMathNumber
}