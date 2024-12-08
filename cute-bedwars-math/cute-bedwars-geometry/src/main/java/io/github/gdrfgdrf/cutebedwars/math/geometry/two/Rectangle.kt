package io.github.gdrfgdrf.cutebedwars.math.geometry.two

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.common.maxOf
import io.github.gdrfgdrf.cutebedwars.abstracts.math.common.minOf
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.two.ILine2D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.two.IShape2D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.two.IPoint2D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.two.IRectangle
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@ServiceImpl("rectangle", needArgument = true)
class Rectangle(
    override val pos1: IPoint2D,
    override val pos2: IPoint2D
) : IRectangle {
    constructor(argumentSet: ArgumentSet) : this(
        argumentSet.args[0] as IPoint2D,
        argumentSet.args[1] as IPoint2D
    )

    init {
        if (pos1 == pos2) {
            throw IllegalArgumentException("pos1 and pos2 cannot be the same")
        }
    }

    override val otherShapes: MutableList<IShape2D> = arrayListOf()

    override fun divide2d(step: IMathNumber): List<IPoint2D> {
        val result = arrayListOf<IPoint2D>().apply {
            addAll(a.divide2d(step))
            addAll(b.divide2d(step))
            addAll(c.divide2d(step))
            addAll(d.divide2d(step))

            val otherPoints = otherShapes.stream()
                .map {
                    it.divide2d(step)
                }.flatMap {
                    it.stream()
                }.toList()
            addAll(otherPoints)
        }
        return result
    }

    override fun contains(point2d: IPoint2D): Boolean {
        val minX = minOf(pos1.x, pos2.x)
        val maxX = maxOf(pos1.x, pos2.x)

        val minY = minOf(pos1.y, pos2.y)
        val maxY = maxOf(pos1.y, pos2.y)

        val x = point2d.x
        val y = point2d.y

        val first1 = minX <= x
        val first2 = x <= maxX
        val first = first1 && first2
        if (!first) {
            return false
        }

        val second1 = minY <= y
        val second2 = y <= maxY
        val second = second1 && second2
        return second
    }

    override fun addShape(shape2d: IShape2D) {
        shape2d.points2d.forEach {
            if (!contains(it)) {
                throw IllegalArgumentException("one of the shape's points is not in the rectangle")
            }
        }
        otherShapes.add(shape2d)
    }

    override fun addLine(start: IPoint2D, end: IPoint2D) {
        val line2d = ILine2D.new(start, end)
        addShape(line2d)
    }

}