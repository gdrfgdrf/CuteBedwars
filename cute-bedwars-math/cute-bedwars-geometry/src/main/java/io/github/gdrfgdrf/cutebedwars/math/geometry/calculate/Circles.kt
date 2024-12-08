package io.github.gdrfgdrf.cutebedwars.math.geometry.calculate

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.two.IPoint2D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.calculate.ICircles
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
        val resultX = x0 + R * (a + offset).radians().cos()
        val resultY = y0 + R * (a + offset).radians().sin()

        return IPoint2D.new(resultX, resultY)
    }
}