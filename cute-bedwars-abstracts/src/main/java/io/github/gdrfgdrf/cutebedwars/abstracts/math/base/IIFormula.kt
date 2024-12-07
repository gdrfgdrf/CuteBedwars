package io.github.gdrfgdrf.cutebedwars.abstracts.math.base

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber

interface IIFormula {
    fun calculate(vararg any: Any): IMathNumber
}