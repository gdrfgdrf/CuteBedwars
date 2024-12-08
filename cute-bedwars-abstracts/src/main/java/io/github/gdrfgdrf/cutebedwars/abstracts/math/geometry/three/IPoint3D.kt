package io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.base.IPoint
import io.github.gdrfgdrf.cutebedwars.abstracts.math.common.mathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.two.IPoint2D
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@Service("point_3d", singleton = false)
interface IPoint3D : IPoint {
    val x: IMathNumber
    val y: IMathNumber
    val z: IMathNumber

    fun two(): IPoint2D
    fun vector3i(other: IPoint3D): IVector3i
    fun coordinate(): Coordinate

    companion object {
        fun new(x: IMathNumber, y: IMathNumber, z: IMathNumber): IPoint3D =
            Mediator.get(IPoint3D::class.java, ArgumentSet(arrayOf(x, y, z)))!!

        fun new(x: Number, y: Number, z: Number): IPoint3D = new(
            x.mathNumber(),
            y.mathNumber(),
            z.mathNumber()
        )

        fun new(coordinate: Coordinate): IPoint3D = new(
            coordinate.x,
            coordinate.y,
            coordinate.z
        )
    }
}