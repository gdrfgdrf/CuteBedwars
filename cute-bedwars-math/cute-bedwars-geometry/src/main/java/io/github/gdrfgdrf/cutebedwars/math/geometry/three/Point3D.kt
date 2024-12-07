package io.github.gdrfgdrf.cutebedwars.math.geometry.three

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three.IPoint3D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three.IVector3i
import io.github.gdrfgdrf.cutebedwars.abstracts.math.common.mathNumber
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate
import io.github.gdrfgdrf.cutebedwars.math.common.Vector3i
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@ServiceImpl("point_3d", needArgument = true)
class Point3D(
    override val x: IMathNumber,
    override val y: IMathNumber,
    override val z: IMathNumber
) : IPoint3D {
    constructor(argumentSet: ArgumentSet): this(
        argumentSet.args[0] as IMathNumber,
        argumentSet.args[1] as IMathNumber,
        argumentSet.args[2] as IMathNumber,
    )

    override val step: Int = 3
    override val numbers: Array<IMathNumber> = arrayOf(x, y, z)

    override fun vector3i(other: IPoint3D): IVector3i {
        return Vector3i.of(other.x - x, other.y - y, other.z - z)
    }

    override fun coordinate(): Coordinate {
        val coordinate = Coordinate()
        coordinate.x = x.toDouble()
        coordinate.y = y.toDouble()
        coordinate.z = z.toDouble()

        return coordinate
    }

    override fun toString(): String {
        return "($x, $y, $z)"
    }

    companion object {
        fun of(x: IMathNumber, y: IMathNumber, z:  IMathNumber) = Point3D(x, y , z)
        fun of(x: Number, y: Number, z: Number) = Point3D(x.mathNumber(), y.mathNumber(), z.mathNumber())
    }
}