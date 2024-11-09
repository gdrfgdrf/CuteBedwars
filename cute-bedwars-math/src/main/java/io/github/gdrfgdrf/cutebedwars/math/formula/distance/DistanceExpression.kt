package io.github.gdrfgdrf.cutebedwars.math.formula.distance

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.math.common.Arguments
import io.github.gdrfgdrf.cutebedwars.math.base.IExpression
import io.github.gdrfgdrf.cutebedwars.math.common.MathNumber

class DistanceExpression : IExpression {
    override fun calculate(arguments: Arguments): IMathNumber {
        var result: IMathNumber? = null
        var firstNode: IMathNumber? = null
        var secondNode: IMathNumber? = null

        val length = arguments.length()
        for (i in 0 until length) {
            val x = arguments[i].value as IMathNumber

            if (firstNode == null) {
                firstNode = x
            } else {
                if (secondNode == null) {
                    secondNode = x

                    result = result?.plus(firstNode.minus(secondNode).pow(2))
                        ?: firstNode.minus(secondNode).pow(2)
                    firstNode = null
                    secondNode = null
                }
            }
        }

        if (result == null) {
            throw IllegalArgumentException()
        }
        return result
    }
}