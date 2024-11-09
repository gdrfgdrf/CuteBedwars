package io.github.gdrfgdrf.cutebedwars.math.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.enums.ISteps
import io.github.gdrfgdrf.cutebedwars.math.common.MathNumber
import io.github.gdrfgdrf.cutebedwars.math.common.mathNumber
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumServiceImpl

@EnumServiceImpl("steps")
enum class Steps(override val mathNumber: IMathNumber) : ISteps {
    _0_0_1(0.01),
    ONE(1);

    constructor(number: Number): this(number.mathNumber())
}