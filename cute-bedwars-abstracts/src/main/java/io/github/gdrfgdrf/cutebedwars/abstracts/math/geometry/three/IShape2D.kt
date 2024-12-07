package io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.base.IPoint
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.base.IShape
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.two.IPoint2D
import java.util.*

interface IShape2D : IShape {
    override val points: Array<IPoint>
        get() = Arrays.stream(points2d)
            .map {
                it as IPoint
            }
            .toList()
            .toTypedArray()
    val points2d: Array<IPoint2D>

    override fun divide(step: IMathNumber): List<IPoint> {
        return divide2d(step)
    }

    fun divide2d(step: IMathNumber): List<IPoint2D>
}