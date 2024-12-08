package io.github.gdrfgdrf.cutebedwars.math.geometry.calculate

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.calculate.IRotates
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.two.IPoint2D
import io.github.gdrfgdrf.cutebedwars.math.geometry.two.Point2D
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl

@ServiceImpl("rotates")
object Rotates : IRotates {
    override fun rotatePoint2d(center: IPoint2D, origin: IPoint2D, theta: IMathNumber): IPoint2D {
        val x = origin.x
        val y = origin.y
        val rx = center.x
        val ry = center.y

        val cosTheta = theta.radians().cos()
        val sinTheta = theta.radians().sin()

        val resultX = cosTheta * (x - rx) - sinTheta * (y - ry) + rx
        val resultY = sinTheta * (x - rx) + cosTheta * (y - ry) + ry

        return Point2D.of(resultX, resultY)
    }
}