package io.github.gdrfgdrf.cutebedwars.math.geometry.three

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.common.maxOf
import io.github.gdrfgdrf.cutebedwars.abstracts.math.common.minOf
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three.ILine3D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three.IOutlineBox
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three.IPoint3D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three.IShape3D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.two.IPoint2D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.two.IShape2D
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@ServiceImpl("box", needArgument = true, instanceGetter = "create")
class OutlineBox private constructor(
    override var pos1: IPoint3D,
    override var pos2: IPoint3D,
) : IOutlineBox {
    init {
        if (pos1 == pos2) {
            throw IllegalArgumentException("pos1 and pos2 cannot be the same")
        }
    }

    override val otherShapes: MutableList<IShape3D> = arrayListOf()
    override val otherPoints: MutableList<IPoint3D> = arrayListOf()

    override fun divide3d(step: IMathNumber): List<IPoint3D> {
        val result = arrayListOf<IPoint3D>().apply {
            addAll(a.divide3d(step))
            addAll(b.divide3d(step))
            addAll(c.divide3d(step))
            addAll(d.divide3d(step))

            addAll(e.divide3d(step))
            addAll(f.divide3d(step))
            addAll(g.divide3d(step))
            addAll(h.divide3d(step))

            addAll(i.divide3d(step))
            addAll(j.divide3d(step))
            addAll(k.divide3d(step))
            addAll(l.divide3d(step))

            val otherPoints = otherShapes.stream()
                .map {
                    it.divide3d(step)
                }.flatMap {
                    it.stream()
                }.toList()
            addAll(otherPoints)
            addAll(this@OutlineBox.otherPoints)
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

    override fun addShape(shape3d: IShape3D) {
        shape3d.points3d.forEach {
            if (!contains(it)) {
                throw IllegalArgumentException("one of the shape's points is not in the box")
            }
        }
        otherShapes.add(shape3d)
    }

    override fun addShape(shape2d: IShape2D, step: IMathNumber, y: IMathNumber) {
        shape2d.points2d.forEach {
            val point3d = IPoint3D.new(it.x, y, it.y)
            if (!contains(point3d)) {
                throw IllegalArgumentException("one of the shape's points is not in the box")
            }
        }
        shape2d.divide3d(step, y).forEach {
            otherPoints.add(it)
        }
    }

    override fun addLine(start: IPoint3D, end: IPoint3D) {
        val line3d = ILine3D.new(start, end)
        addShape(line3d)
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