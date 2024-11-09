package io.github.gdrfgdrf.cutebedwars.math.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.enums.IPrecisions
import io.github.gdrfgdrf.cutebedwars.abstracts.math.enums.ISteps
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumServiceImpl

@EnumServiceImpl("precisions")
enum class Precisions(
    override val digit: Int,
    override val step: ISteps,
    override val pattern: String
) : IPrecisions {
    TWO(2, Steps.ONE, "#.00")

    ;

    override fun next(mathNumber: IMathNumber): IMathNumber {
        return mathNumber.cut(this).plus(step.mathNumber)
    }
}