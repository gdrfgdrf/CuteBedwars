package io.github.gdrfgdrf.cutebedwars.abstracts.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.items.IItem
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumService

@EnumService("items")
interface IItems {
    fun item(): IItem

    companion object {
        fun valueOf(name: String): IItems = Mediator.valueOf(IItems::class.java, name)!!
    }
}