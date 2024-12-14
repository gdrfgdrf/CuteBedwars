package io.github.gdrfgdrf.cutebedwars.abstracts.enums

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumService

@EnumService("find_type")
interface IFindType {
    companion object {
        fun valueOf(name: String): IFindType = Mediator.valueOf(IFindType::class.java, name)!!
        fun find(name: String): List<IFindType> = Mediator.search<IFindType>(IFindType::class.java, name)!!
    }
}