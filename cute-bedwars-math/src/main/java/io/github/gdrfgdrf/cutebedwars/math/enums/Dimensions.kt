package io.github.gdrfgdrf.cutebedwars.math.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.math.enums.IDimensions
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumServiceImpl

@EnumServiceImpl("dimensions")
enum class Dimensions(override val int: Int) : IDimensions {
    TWO(2),
    THREE(3)
}