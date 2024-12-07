package io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.base

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber

interface ILine {
    val start: IPoint
    val end: IPoint
    val length: IMathNumber
    val midPoint: IPoint
    val half: ILine
}