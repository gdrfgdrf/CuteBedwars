package io.github.gdrfgdrf.cutebedwars.math.formula.distance

import io.github.gdrfgdrf.cutebedwars.math.common.Arguments
import io.github.gdrfgdrf.cutebedwars.math.base.IExpression
import io.github.gdrfgdrf.cutebedwars.math.common.MathNumber

class DistanceExpression : IExpression {
    override fun calculate(arguments: Arguments): MathNumber {
        var result: MathNumber? = null
        var firstNode: MathNumber? = null
        var secondNode: MathNumber? = null

        val length = arguments.length()
        for (i in 0 until length) {
            val x = arguments[i].value as MathNumber

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