package io.github.gdrfgdrf.cutebedwars.math.base

import io.github.gdrfgdrf.cutebedwars.math.common.Argument
import io.github.gdrfgdrf.cutebedwars.math.common.MathNumber

interface IFormula {
    fun calculate(vararg any: Any): MathNumber {
        val arguments = arrayListOf<Argument>()
        any.forEach {
            arguments.add(Argument.of(it))
        }
        return calculate(*arguments.toTypedArray())
    }

    fun calculate(vararg argument: Argument): MathNumber
}