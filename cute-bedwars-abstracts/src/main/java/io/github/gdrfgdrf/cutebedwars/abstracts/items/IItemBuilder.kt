package io.github.gdrfgdrf.cutebedwars.abstracts.items

import de.tr7zw.changeme.nbtapi.iface.ReadWriteItemNBT
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("item_builder", singleton = false)
interface IItemBuilder {
    val properties: IItemProperties
    fun modify(modifier: IItemProperties.() -> Unit): IItemBuilder
    fun build(special: Boolean = true, nbtModifier: ((ReadWriteItemNBT) -> Unit)? = null): IItem

    companion object {
        fun new(): IItemBuilder = Mediator.get(IItemBuilder::class.java)!!
    }
}