package io.github.gdrfgdrf.cutebedwars.math.calculate

import io.github.gdrfgdrf.cutebedwars.abstracts.math.base.IPoint3D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.calculate.ICuboids
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl

@ServiceImpl("cuboids")
object Cuboids : ICuboids {
    override fun geometricCenter(A: IPoint3D, B: IPoint3D): IPoint3D {
        val x1 = A.x
        val y1 = A.y
        val z1 = A.z

        val x2 = B.x
        val y2 = B.y
        val z2 = B.z

        val resultX = (x1 + x2) / 2
        val resultY = (y1 + y2) / 2
        val resultZ = (z1 + z2) / 2

        return IPoint3D.new(resultX, resultY, resultZ)
    }
}