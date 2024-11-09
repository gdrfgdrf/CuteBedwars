package io.github.gdrfgdrf.cutebedwars.abstracts.math.enums

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumService

@EnumService("dimensions")
interface IDimensions {
    val int: Int

    companion object {
        fun valueOf(name: String): IDimensions = Mediator.valueOf(IDimensions::class.java, name)!!
    }
}