package io.github.gdrfgdrf.cutebedwars.math.geometry.calculate

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three.IPoint3D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.calculate.IBoxes
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl

@ServiceImpl("boxes")
object Boxes : IBoxes {
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

    override fun size(A: IPoint3D, B: IPoint3D): IMathNumber {
        val x1 = A.x
        val y1 = A.y
        val z1 = A.z

        val x2 = B.x
        val y2 = B.y
        val z2 = B.z

        return (x2 - x1).abs() * (y2 - y1).abs() * (z2 - z1).abs()
    }
}