package io.github.gdrfgdrf.cutebedwars.math.common

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.base.IPoint3D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.common.IVector3i
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@ServiceImpl("point_3d", instanceGetter = "new")
class Point3D(
    override val x: IMathNumber,
    override val y: IMathNumber,
    override val z: IMathNumber
) : IPoint3D {
    override val step: Int = 3
    override val all: Array<IMathNumber> = arrayOf(x, y, z)

    override fun vector3i(other: IPoint3D): IVector3i {
        return Vector3i.of(other.x - x, other.y - y, other.z - z)
    }

    override fun toString(): String {
        return "($x, $y, $z)"
    }

    companion object {
        @JvmStatic
        fun new(argumentSet: ArgumentSet): IPoint3D {
            return of(
                argumentSet.args[0] as Number,
                argumentSet.args[1] as Number,
                argumentSet.args[2] as Number
            )
        }

        fun of(x: IMathNumber, y: IMathNumber, z:  IMathNumber) = Point3D(x, y , z)
        fun of(x: Number, y: Number, z: Number) = Point3D(x.mathNumber(), y.mathNumber(), z.mathNumber())
    }
}