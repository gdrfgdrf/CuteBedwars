package io.github.gdrfgdrf.cutebedwars.math

import io.github.gdrfgdrf.cutebedwars.math.base.AbstractRangeExpression
import io.github.gdrfgdrf.cutebedwars.math.base.IExpression
import io.github.gdrfgdrf.cutebedwars.math.common.*

class Sigma(
    private val range: AbstractRangeExpression,
    private val expression: IExpression
) {
    fun calculate(argumentsWithoutI: Arguments): MathNumber {
        val iArray = arrayListOf<MathNumber>()
        range.forEach { i ->
            iArray.add(i)
        }

        val newArguments = Arguments()
        for (x in 0 until argumentsWithoutI.length()) {
            newArguments.add(argumentsWithoutI[x])
        }

        return expression.calculate(newArguments)
    }


}