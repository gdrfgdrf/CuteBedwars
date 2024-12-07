package io.github.gdrfgdrf.cutebedwars.math.geometry.three

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.base.IPoint
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three.IOutlineBox
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three.IPoint3D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three.ILine3D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.common.maxOf
import io.github.gdrfgdrf.cutebedwars.abstracts.math.common.minOf
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@ServiceImpl("box", needArgument = true, instanceGetter = "create")
class OutlineBox private constructor(
    override var pos1: IPoint3D,
    override var pos2: IPoint3D,
) : IOutlineBox {
    override val A: IPoint3D
    override val B: IPoint3D
    override val O: IPoint3D
    override val B_: IPoint3D
    override val C_: IPoint3D
    override val D_: IPoint3D

    override val otherLines: MutableList<ILine3D> = arrayListOf()

    init {
        val x1 = pos1.x
        val y1 = pos1.y
        val z1 = pos1.z

        val x2 = pos2.x
        val y2 = pos2.y
        val z2 = pos2.z

        A = IPoint3D.new(x1, y1, z2)
        B = IPoint3D.new(x1, y2, z2)
        O = IPoint3D.new(x2, y1, z2)

        B_ = IPoint3D.new(x1, y2, z1)
        C_ = IPoint3D.new(x2, y2, z1)
        D_ = IPoint3D.new(x2, y1, z1)
    }

    override fun divide(step: IMathNumber): List<IPoint> {
        val result = arrayListOf<IPoint>().apply {
            addAll(a.divide(step))
            addAll(b.divide(step))
            addAll(c.divide(step))
            addAll(d.divide(step))

            addAll(e.divide(step))
            addAll(f.divide(step))
            addAll(g.divide(step))
            addAll(h.divide(step))

            addAll(i.divide(step))
            addAll(j.divide(step))
            addAll(k.divide(step))
            addAll(l.divide(step))

            val otherPoints = otherLines.stream()
                .map {
                    it.divide(step)
                }.flatMap {
                    it.stream()
                }.toList()
            addAll(otherPoints)
        }
        return result
    }

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

    override fun addLine(a: IPoint3D, b: IPoint3D) {
        if (!contains(a) || !contains(b)) {
            throw IllegalArgumentException("a or b is not in the box")
        }

        val line3d = ILine3D.new(a, b)
        otherLines.add(line3d)
    }

    override fun toString(): String {
        return "$pos1 -> $pos2 (OutlineBox)"
    }

    companion object {
        fun create(pos1: IPoint3D, pos2: IPoint3D): IOutlineBox {
            if (pos1.x == pos2.x || pos1.y == pos2.y || pos1.z == pos2.z) {
                throw IllegalArgumentException("pos1 and pos2 is on a same plane, cannot build a outline box")
            }
            // 互换 x 以保证 pos1 的 x 永远大于 pos2 的 x
            if (pos1.x < pos2.x) {
                val newPos1 = IPoint3D.new(pos2.x, pos1.y, pos1.z)
                val newPos2 = IPoint3D.new(pos1.x, pos2.y, pos2.z)

                return create(newPos1, newPos2)
            }
            // 互换 y 以保证 pos1 的 y 永远小于 pos2 的 y
            if (pos1.y > pos2.y) {
                val newPos1 = IPoint3D.new(pos1.x, pos2.y, pos1.z)
                val newPos2 = IPoint3D.new(pos2.x, pos1.y, pos2.z)

                return create(newPos1, newPos2)
            }
            // 互换 z 以保证 pos1 永远在 pos2 的上方
            if (pos1.z < pos2.z) {
                val newPos1 = IPoint3D.new(pos1.x, pos1.y, pos2.z)
                val newPos2 = IPoint3D.new(pos2.x, pos2.y, pos1.z)

                return create(newPos1, newPos2)
            }
            return OutlineBox(pos1, pos2)
        }

        @JvmStatic
        fun create(argumentSet: ArgumentSet): IOutlineBox {
            return create(argumentSet.args[0] as IPoint3D, argumentSet.args[1] as IPoint3D)
        }
    }
}