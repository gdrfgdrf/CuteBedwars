package io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.two

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.base.IPoint
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.base.IShape
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three.IPoint3D
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

    /**
     * 将 divide2d 返回的所有二维点转换成所有具有同一 Y 轴的三维点
     */
    fun divide3d(step: IMathNumber, y: IMathNumber): List<IPoint3D> {
        return divide2d(step).stream()
            .map {
                val x = it.x
                val z = it.y
                IPoint3D.new(x, y, z)
            }
            .toList()
    }
    fun divide2d(step: IMathNumber): List<IPoint2D>
}