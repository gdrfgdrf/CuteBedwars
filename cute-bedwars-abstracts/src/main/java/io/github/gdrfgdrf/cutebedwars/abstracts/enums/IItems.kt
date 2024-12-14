package io.github.gdrfgdrf.cutebedwars.abstracts.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.items.item.IBuiltItem
import io.github.gdrfgdrf.cutebedwars.abstracts.items.item.IItem
import io.github.gdrfgdrf.cutebedwars.abstracts.items.item.ISpecialBuiltItem
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumService

@EnumService("items")
interface IItems {
    val item: IItem

    fun builtItem(): IBuiltItem
    fun special(): ISpecialBuiltItem

    companion object {
        fun valueOf(name: String): IItems = Mediator.valueOf(IItems::class.java, name)!!
    }
}