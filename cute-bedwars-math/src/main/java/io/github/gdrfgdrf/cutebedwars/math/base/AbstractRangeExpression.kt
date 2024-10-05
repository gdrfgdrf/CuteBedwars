package io.github.gdrfgdrf.cutebedwars.math.base

import io.github.gdrfgdrf.cutebedwars.math.common.MathNumber

abstract class AbstractRangeExpression {
    abstract fun start(): MathNumber
    abstract fun end(): MathNumber
    abstract fun forEach(block: (MathNumber) -> Unit)
    abstract fun forEachIndexed(block: (Int, MathNumber) -> Unit)
}