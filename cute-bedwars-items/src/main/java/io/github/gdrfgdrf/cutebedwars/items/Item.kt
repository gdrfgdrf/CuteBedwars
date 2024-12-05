package io.github.gdrfgdrf.cutebedwars.items

import io.github.gdrfgdrf.cutebedwars.abstracts.items.ICommonItem
import io.github.gdrfgdrf.cutebedwars.abstracts.items.IItem
import io.github.gdrfgdrf.cutebedwars.abstracts.items.IItemCollections
import io.github.gdrfgdrf.cutebedwars.abstracts.items.IItemProperties
import io.github.gdrfgdrf.cutebedwars.items.item.CommonItem
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

open class Item(
    private val itemStack: ItemStack,
    override val properties: IItemProperties
) : IItem {
    protected var appliedName = false

    init {
        properties.droppable = true
    }

    override fun tryGive(player: Player, amount: Int, slotIndex: Int): Pair<Boolean, ICommonItem> {
        if (!appliedName) {
            properties.applyTo(itemStack)
            appliedName = true
        }

        val commonItem = IItemCollections.instance().find(player, itemStack) ?: return true to give(player, amount, slotIndex)
        return false to commonItem
    }

    override fun give(player: Player, amount: Int, slotIndex: Int): ICommonItem {
        if (!appliedName) {
            properties.applyTo(itemStack)
            appliedName = true
        }

        val commonItem = CommonItem(
            itemStack.clone(),
            this,
            player,
            amount
        )
        commonItem.give(slotIndex)
        properties.onGiven?.invoke(player, commonItem)

        return commonItem
    }
}