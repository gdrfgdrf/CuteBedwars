package io.github.gdrfgdrf.cutebedwars.math.calculate

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.common.times

object Equations {
    /**
     * 求一元二次方程的实数解
     */
    fun quadraticOneVariable(a: IMathNumber, b: IMathNumber, c: IMathNumber): Pair<IMathNumber?, IMathNumber?> {
        var x1: IMathNumber? = null
        var x2: IMathNumber? = null

        val delta = b.pow(2) - 4 * a * c
        if (delta > 0) {
            // 求根公式
            x1 = (-b + delta.sqrt()) / (2 * a)
            x2 = (-b - delta.sqrt()) / (2 * a)
        } else {
            if (delta.equals(0)) {
                // delta 为 0 时的求根公式
                x1 = -(b / (2 * a))
                x2 = x1
            }
        }

        return Pair(x1, x2)
    }
}
