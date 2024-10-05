package io.github.gdrfgdrf.cutebedwars.math.formulas.distance

import io.github.gdrfgdrf.cutebedwars.math.Sigma
import io.github.gdrfgdrf.cutebedwars.math.base.IFormula
import io.github.gdrfgdrf.cutebedwars.math.common.*
import io.github.gdrfgdrf.cutebedwars.math.enums.Dimensions
import io.github.gdrfgdrf.cutebedwars.math.enums.Precisions
import io.github.gdrfgdrf.cutebedwars.math.enums.Spaces

object DistanceFormula : IFormula {
    override fun calculate(vararg argument: Argument): MathNumber {
        val distanceArguments = DistanceArguments(Arguments(*argument))
        val space = distanceArguments.space()
        val dimension = distanceArguments.dimension()

        if (space == Spaces.EUCLIDEAN) {
            val rangeExpression =
                DistanceSigmaRangeExpression(MathNumber.of(1), MathNumber.of(dimension.int), Precisions.TWO)
            val expression = DistanceExpression()

            val sigma = Sigma(rangeExpression, expression)
            return sigma.calculate(distanceArguments.allPoints()).sqrt()
        }

        throw IllegalArgumentException()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val space = Argument(Spaces.EUCLIDEAN)
//        val dimension = Argument(Dimensions.TWO)
        val dimension = Argument(Dimensions.THREE)

//        val firstPoint = Point2D(MathNumber.number(10), MathNumber.number(20))
//        val secondPoint = Point2D(MathNumber.number(-10), MathNumber.number(-20))

        val firstPoint = Point3D(MathNumber.of(10), MathNumber.of(20), MathNumber.of(30))
        val secondPoint = Point3D(MathNumber.of(-10), MathNumber.of(-20), MathNumber.of(-30))

        val result = calculate(space, dimension, Argument(firstPoint), Argument(secondPoint))
        println(result.number)
    }
}