package io.github.gdrfgdrf.cutebedwars.items.builder

import de.tr7zw.changeme.nbtapi.NBT
import de.tr7zw.changeme.nbtapi.iface.ReadWriteItemNBT
import io.github.gdrfgdrf.cutebedwars.abstracts.items.item.IBuiltItem
import io.github.gdrfgdrf.cutebedwars.abstracts.items.builder.IItemBuilder
import io.github.gdrfgdrf.cutebedwars.abstracts.items.item.ISpecialBuiltItem
import io.github.gdrfgdrf.cutebedwars.abstracts.items.property.IItemProperties
import io.github.gdrfgdrf.cutebedwars.items.item.BuiltItem
import io.github.gdrfgdrf.cutebedwars.items.item.SpecialBuiltItem
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import org.bukkit.inventory.ItemStack

@ServiceImpl("item_builder")
class ItemBuilder : IItemBuilder {
    override val properties: IItemProperties = IItemProperties.create {}

    override fun modify(modifier: IItemProperties.() -> Unit): IItemBuilder {
        modifier(properties)
        return this
    }

    override fun build(nbtModifier: ((ReadWriteItemNBT) -> Unit)?): IBuiltItem {
        properties.check()

        val itemStack = ItemStack(properties.material)
        properties.applyTo(itemStack, false)

        if (nbtModifier != null) {
            NBT.modify(itemStack, nbtModifier)
        }

        val item = BuiltItem(itemStack, properties)
        return item
    }

    override fun buildSpecial(nbtModifier: ((ReadWriteItemNBT) -> Unit)?): ISpecialBuiltItem {
        properties.check()

        val itemStack = ItemStack(properties.material)
        properties.applyTo(itemStack, false)

        if (nbtModifier != null) {
            NBT.modify(itemStack, nbtModifier)
        }

        val specialItem = SpecialBuiltItem(itemStack, properties)
        return specialItem
    }
}