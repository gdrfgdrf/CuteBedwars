package io.github.gdrfgdrf.cutebedwars.math.common

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.base.IPoint3D
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@ServiceImpl("point_3d", instanceGetter = "new")
class Point3D(
    private val x: IMathNumber,
    private val y: IMathNumber,
    private val z: IMathNumber
) : IPoint3D {
    override val step: Int = 3
    override val all: Array<IMathNumber> = arrayOf(x, y, z)

    companion object {
        @JvmStatic
        fun new(argumentSet: ArgumentSet): IPoint3D {
            return of(
                argumentSet.args[0] as Number,
                argumentSet.args[1] as Number,
                argumentSet.args[2] as Number
            )
        }

        fun of(x: Number, y: Number, z: Number) = Point3D(x.mathNumber(), y.mathNumber(), z.mathNumber())
    }
}