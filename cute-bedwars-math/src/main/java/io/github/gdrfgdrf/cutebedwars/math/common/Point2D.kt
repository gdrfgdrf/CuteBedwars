package io.github.gdrfgdrf.cutebedwars.math.common

import io.github.gdrfgdrf.cutebedwars.math.base.IPoint

class Point2D(
    private val x: MathNumber,
    private val y: MathNumber
) : IPoint {
    override fun step(): Int = 2

    override fun all(): Array<MathNumber> {
        return arrayOf(x, y)
    }

}