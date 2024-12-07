package io.github.gdrfgdrf.cutebedwars.math.geometry.two

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.two.IPoint2D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.common.mathNumber
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@ServiceImpl("point_2d", needArgument = true)
class Point2D private constructor(
    override val x: IMathNumber,
    override val y: IMathNumber
) : IPoint2D {
    constructor(argumentSet: ArgumentSet): this(
        argumentSet.args[0] as IMathNumber,
        argumentSet.args[1] as IMathNumber
    )

    override val step: Int = 2
    override val numbers: Array<IMathNumber> = arrayOf(x, y)

    init {
        if (x.toDouble().isNaN() || y.toDouble().isNaN()) {
            throw IllegalArgumentException("x or y is NaN")
        }
    }

    override fun toString(): String {
        return "($x, $y)"
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }
        if (other !is IPoint2D) {
            return false
        }

        if (x == other.x && y == other.y) {
            return true
        }
        return false
    }

    override fun hashCode(): Int {
        var result = x.hashCode()
        result = 31 * result + y.hashCode()
        result = 31 * result + step
        return result
    }

    companion object {
        fun of(x: Number, y: Number) = of(x.mathNumber(), y.mathNumber())
        fun of(x: IMathNumber, y: IMathNumber) = Point2D(x, y)
    }
}