package io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.base.IPoint
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.base.IShape
import java.util.*

interface IShape3D : IShape {
    override val points: Array<IPoint>
        get() = Arrays.stream(points3d)
            .map {
                it as IPoint
            }
            .toList()
            .toTypedArray()
    val points3d: Array<IPoint3D>

    override fun divide(step: IMathNumber): List<IPoint> {
        return divide3d(step)
    }

    fun divide3d(step: IMathNumber): List<IPoint3D>
}