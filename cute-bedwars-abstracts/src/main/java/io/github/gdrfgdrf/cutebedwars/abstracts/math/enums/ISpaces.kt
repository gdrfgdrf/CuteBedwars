package io.github.gdrfgdrf.cutebedwars.abstracts.math.enums

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumService

@EnumService("spaces")
interface ISpaces {
    companion object {
        fun valueOf(name: String): ISpaces = Mediator.valueOf(ISpaces::class.java, name)!!
    }
}