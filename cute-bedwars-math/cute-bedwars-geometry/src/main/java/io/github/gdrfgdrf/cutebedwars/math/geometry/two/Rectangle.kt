package io.github.gdrfgdrf.cutebedwars.math.geometry.two

import io.github.gdrfgdrf.cutebedwars.abstracts.math.common.maxOf
import io.github.gdrfgdrf.cutebedwars.abstracts.math.common.minOf
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.two.ILine2D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.two.IPoint2D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.two.IRectangle
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@ServiceImpl("rectangle", needArgument = true)
class Rectangle(
    override val pos1: IPoint2D,
    override val pos2: IPoint2D
) : IRectangle {
    constructor(argumentSet: ArgumentSet): this(
        argumentSet.args[0] as IPoint2D,
        argumentSet.args[1] as IPoint2D
    )

    init {
        if (pos1 == pos2) {
            throw IllegalArgumentException("start point and end point cannot be the same")
        }
    }

    override val otherLines: MutableList<ILine2D> = arrayListOf()

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

    override fun addLine(start: IPoint2D, end: IPoint2D) {
        if (!contains(start) || !contains(end)) {
            throw IllegalArgumentException("a or b is not in the box")
        }

        val line2d = ILine2D.new(start, end)
        otherLines.add(line2d)
    }

}