package io.github.gdrfgdrf.cutebedwars.abstracts.items.builder

import de.tr7zw.changeme.nbtapi.iface.ReadWriteItemNBT
import io.github.gdrfgdrf.cutebedwars.abstracts.items.property.IItemProperties
import io.github.gdrfgdrf.cutebedwars.abstracts.items.item.IBuiltItem
import io.github.gdrfgdrf.cutebedwars.abstracts.items.item.ISpecialBuiltItem
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("item_builder", singleton = false)
interface IItemBuilder {
    val properties: IItemProperties
    fun modify(modifier: IItemProperties.() -> Unit): IItemBuilder
    fun build(nbtModifier: ((ReadWriteItemNBT) -> Unit)? = null): IBuiltItem
    fun buildSpecial(nbtModifier: ((ReadWriteItemNBT) -> Unit)? = null): ISpecialBuiltItem

    companion object {
        fun new(): IItemBuilder = Mediator.get(IItemBuilder::class.java)!!
    }
}