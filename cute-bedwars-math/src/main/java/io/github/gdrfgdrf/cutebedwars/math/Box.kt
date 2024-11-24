package io.github.gdrfgdrf.cutebedwars.math

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IBox
import io.github.gdrfgdrf.cutebedwars.abstracts.math.base.IPoint3D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.maxOf
import io.github.gdrfgdrf.cutebedwars.abstracts.math.minOf
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@ServiceImpl("box", needArgument = true)
class Box(
    override var pos1: IPoint3D,
    override var pos2: IPoint3D,
) : IBox {
    constructor(argumentSet: ArgumentSet): this(
        argumentSet.args[0] as IPoint3D,
        argumentSet.args[1] as IPoint3D
    )

    override fun contains(point3d: IPoint3D): Boolean {
        val minX = minOf(pos1.x, pos2.x)
        val maxX = maxOf(pos1.x, pos2.x)

        val minY = minOf(pos1.y, pos2.y)
        val maxY = maxOf(pos1.y, pos2.y)

        val minZ = minOf(pos1.z, pos2.z)
        val maxZ = maxOf(pos1.z, pos2.z)

        val x = point3d.x
        val y = point3d.y
        val z = point3d.z

        val first1 = minX <= x
        val first2 = x <= maxX
        val first = first1 && first2
        if (!first) {
            return false
        }

        val second1 = minY <= y
        val second2 = y <= maxY
        val second = second1 && second2
        if (!second) {
            return false
        }

        val third1 = minZ <= z
        val third2 = z <= maxZ
        val third = third1 && third2
        return third
    }
}