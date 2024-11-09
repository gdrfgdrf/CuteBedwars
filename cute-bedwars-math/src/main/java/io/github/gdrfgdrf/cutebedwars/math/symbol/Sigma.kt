package io.github.gdrfgdrf.cutebedwars.math.symbol

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.math.base.AbstractRangeGenerator
import io.github.gdrfgdrf.cutebedwars.math.base.IExpression
import io.github.gdrfgdrf.cutebedwars.math.common.*

class Sigma(
    private val expression: IExpression
) {
    fun calculate(argumentsWithoutI: Arguments): IMathNumber {
        val newArguments = Arguments.empty()
        for (x in 0 until argumentsWithoutI.length()) {
            newArguments.add(argumentsWithoutI[x])
        }

        return expression.calculate(newArguments)
    }


}