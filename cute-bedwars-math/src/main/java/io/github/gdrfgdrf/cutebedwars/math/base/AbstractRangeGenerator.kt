package io.github.gdrfgdrf.cutebedwars.math.base

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber

abstract class AbstractRangeGenerator {
    abstract fun start(): IMathNumber
    abstract fun end(): IMathNumber
    abstract fun forEach(block: (IMathNumber) -> Unit)
    abstract fun forEachIndexed(block: (Int, IMathNumber) -> Unit)
}