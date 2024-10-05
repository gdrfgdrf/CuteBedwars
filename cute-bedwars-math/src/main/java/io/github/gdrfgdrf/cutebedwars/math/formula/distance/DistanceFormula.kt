package io.github.gdrfgdrf.cutebedwars.math.formula.distance

import io.github.gdrfgdrf.cutebedwars.math.symbol.Sigma
import io.github.gdrfgdrf.cutebedwars.math.base.IFormula
import io.github.gdrfgdrf.cutebedwars.math.common.*
import io.github.gdrfgdrf.cutebedwars.math.enums.Dimensions
import io.github.gdrfgdrf.cutebedwars.math.enums.Precisions
import io.github.gdrfgdrf.cutebedwars.math.enums.Spaces
import io.github.gdrfgdrf.cutebedwars.math.generator.SigmaRangeGenerator

object DistanceFormula : IFormula {
    override fun calculate(vararg argument: Argument): MathNumber {
        val distanceArguments = DistanceArguments(*argument)
        val space = distanceArguments.space()

        if (space == Spaces.EUCLIDEAN) {
            val expression = DistanceExpression()
            val sigma = Sigma(expression)
            return sigma.calculate(distanceArguments.allPoints()).sqrt()
        }

        throw IllegalArgumentException()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val space = Spaces.EUCLIDEAN
//        val dimension = Argument(Dimensions.TWO)
        val dimension = Dimensions.THREE

//        val firstPoint = Point2D.of(10, 10)
//        val secondPoint = Point2D.of(-10, -10)

        val firstPoint = Point3D.of(10, 10, 10)
        val secondPoint = Point3D.of(-10, -10, -10)

        val result = calculate(space, dimension, firstPoint, secondPoint)
        println(result.number)
    }
}