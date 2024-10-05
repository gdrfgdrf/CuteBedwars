package io.github.gdrfgdrf.cutebedwars.math.common

import io.github.gdrfgdrf.cutebedwars.math.base.IPoint

class Point3D(
    private val x: MathNumber,
    private val y: MathNumber,
    private val z: MathNumber
) : IPoint {
    override fun step(): Int = 3

    override fun all(): Array<MathNumber> {
        return arrayOf(x, y, z)
    }

    companion object {
        fun of(x: Number, y: Number, z: Number) = Point3D(x.mathNumber(), y.mathNumber(), z.mathNumber())
    }
}