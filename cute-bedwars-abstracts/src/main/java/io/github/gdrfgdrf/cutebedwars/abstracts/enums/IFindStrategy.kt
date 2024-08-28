package io.github.gdrfgdrf.cutebedwars.abstracts.enums

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumService

@EnumService("find_strategy")
interface IFindStrategy {
    companion object {
        fun valueOf(name: String): IFindStrategy = Mediator.valueOf(IFindStrategy::class.java, name)!!
    }
}