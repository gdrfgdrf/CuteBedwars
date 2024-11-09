package io.github.gdrfgdrf.cutebedwars.math.formula.distance

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IDistanceFormula
import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.math.symbol.Sigma
import io.github.gdrfgdrf.cutebedwars.math.base.IFormula
import io.github.gdrfgdrf.cutebedwars.math.common.*
import io.github.gdrfgdrf.cutebedwars.math.enums.Dimensions
import io.github.gdrfgdrf.cutebedwars.math.enums.Spaces
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl

@ServiceImpl("distance_formula")
object DistanceFormula : IFormula, IDistanceFormula {
    override fun calculate(vararg argument: Argument): IMathNumber {
        val distanceArguments = DistanceArguments.of(*argument)
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
        val secondPoint = Point3D.of(20, 20, 20)

        val result = calculate(space, dimension, firstPoint, secondPoint)
        println(result.number)
    }
}