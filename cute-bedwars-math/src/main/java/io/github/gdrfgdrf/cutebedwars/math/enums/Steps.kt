package io.github.gdrfgdrf.cutebedwars.math.enums

import io.github.gdrfgdrf.cutebedwars.math.common.MathNumber
import io.github.gdrfgdrf.cutebedwars.math.common.mathNumber

enum class Steps(val mathNumber: MathNumber) {
    _0_0_1(0.01),
    ONE(1);

    constructor(number: Number): this(number.mathNumber())
}