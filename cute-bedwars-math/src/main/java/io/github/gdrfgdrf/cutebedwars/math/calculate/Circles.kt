package io.github.gdrfgdrf.cutebedwars.math.calculate

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.base.IPoint2D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.calculate.ICircles
import io.github.gdrfgdrf.cutebedwars.abstracts.math.mathNumber
import io.github.gdrfgdrf.cutebedwars.math.common.Point2D
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl

@ServiceImpl("circles")
object Circles : ICircles {
    override fun getAPointOnTheCircleFromTheAngle(
        x0: IMathNumber,
        y0: IMathNumber,
        R: IMathNumber,
        a: IMathNumber,
        offset: IMathNumber
    ): IPoint2D {
        val resultX = x0 + R * (a + offset).toRadians().cos()
        val resultY = y0 + R * (a + offset).toRadians().sin()

        return Point2D.of(resultX, resultY)
    }
}