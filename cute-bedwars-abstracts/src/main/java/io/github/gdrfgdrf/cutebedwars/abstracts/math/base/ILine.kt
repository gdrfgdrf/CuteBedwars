package io.github.gdrfgdrf.cutebedwars.abstracts.math.base

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber

interface ILine : IShape {
    override val points: Array<IPoint>
        get() = arrayOf(start, end)

    val start: IPoint
    val end: IPoint
    val length: IMathNumber
    val midPoint: IPoint
    val half: ILine
}