package io.github.gdrfgdrf.cutebedwars.abstracts.math.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumService

@EnumService("precisions")
interface IPrecisions {
    val digit: Int
    val step: ISteps
    val pattern: String

    fun next(mathNumber: IMathNumber): IMathNumber
}