package io.github.gdrfgdrf.cutebedwars.math.base

import io.github.gdrfgdrf.cutebedwars.abstracts.math.base.IIFormula
import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.math.common.Argument

interface IFormula : IIFormula {
    override fun calculate(vararg any: Any): IMathNumber {
        val arguments = arrayListOf<Argument>()
        any.forEach {
            arguments.add(Argument.of(it))
        }
        return calculate(*arguments.toTypedArray())
    }

    fun calculate(vararg argument: Argument): IMathNumber
}