package io.github.gdrfgdrf.cutebedwars.abstracts.math.calculate

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("equations")
@KotlinSingleton
interface IEquations {
    /**
     * 求一元二次方程的实数解
     */
    fun quadraticOneVariable(
        a: IMathNumber,
        b: IMathNumber,
        c: IMathNumber
    ): Pair<IMathNumber?, IMathNumber?>
}