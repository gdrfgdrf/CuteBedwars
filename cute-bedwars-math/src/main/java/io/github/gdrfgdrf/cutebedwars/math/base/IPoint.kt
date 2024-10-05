package io.github.gdrfgdrf.cutebedwars.math.base

import io.github.gdrfgdrf.cutebedwars.math.common.MathNumber

interface IPoint {
    fun step(): Int
    fun all(): Array<MathNumber>
}