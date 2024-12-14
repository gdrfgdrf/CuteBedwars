package io.github.gdrfgdrf.cutebedwars.items.item

import io.github.gdrfgdrf.cutebedwars.abstracts.items.ICommonItem
import io.github.gdrfgdrf.cutebedwars.abstracts.items.IItemCollections
import io.github.gdrfgdrf.cutebedwars.abstracts.items.IItemProperties
import io.github.gdrfgdrf.cutebedwars.items.GivenItem
import io.github.gdrfgdrf.cutebedwars.items.Item
import io.github.gdrfgdrf.cutebedwars.items.ItemCollections
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class SpecialItem(private val itemStack: ItemStack, properties: IItemProperties) : Item(
    itemStack,
    properties
) {
    init {
        properties.droppable = false
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

        val givenItem = GivenItem(
            itemStack.clone(),
            this,
            player,
            amount
        )
        ItemCollections.add(player, givenItem)
        givenItem.give(slotIndex)
        properties.onGiven?.invoke(player, givenItem)

        return givenItem
    }
}