package io.github.gdrfgdrf.cutebedwars.math.calculate

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.base.IPoint
import io.github.gdrfgdrf.cutebedwars.abstracts.math.base.IPoint2D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.base.IPoint3D
import io.github.gdrfgdrf.cutebedwars.math.common.Point2D

object Circles {

    /**
     * 根据角度获取圆上一点，设圆心为 x0, y0，半径为 R，角度为 a
     * 圆上一点 x' = x0 + R * cos(a)
     * 圆上一点 y' = y0 + R * sin(a)
     */
    fun getAPointOnTheCircleFromTheAngle(x0: IMathNumber, y0: IMathNumber, R: IMathNumber, a: IMathNumber): IPoint2D {
        val resultX = x0 + R * a.cos()
        val resultY = y0 + R * a.sin()

        return Point2D.of(resultX, resultY)
    }


}