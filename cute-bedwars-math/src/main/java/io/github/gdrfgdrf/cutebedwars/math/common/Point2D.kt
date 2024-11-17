package io.github.gdrfgdrf.cutebedwars.math.common

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.base.IPoint
import io.github.gdrfgdrf.cutebedwars.abstracts.math.mathNumber

class Point2D(
    private val x: IMathNumber,
    private val y: IMathNumber
) : IPoint {
    override val step: Int = 2
    override val all: Array<IMathNumber> = arrayOf(x, y)

    companion object {
        fun of(x: Number, y: Number) = Point2D(x.mathNumber(), y.mathNumber())
    }
}