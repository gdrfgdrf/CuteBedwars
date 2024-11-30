package io.github.gdrfgdrf.cutebedwars.math.geometry

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.base.IPoint2D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.mathNumber
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
    override val all: Array<IMathNumber> = arrayOf(x, y)

    override fun toString(): String {
        return "($x, $y)"
    }

    companion object {
        fun of(x: Number, y: Number) = of(x.mathNumber(), y.mathNumber())
        fun of(x: IMathNumber, y: IMathNumber) = Point2D(x, y)
    }
}