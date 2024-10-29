package io.github.gdrfgdrf.cutebedwars.items

import io.github.gdrfgdrf.cutebedwars.abstracts.items.ICommonItem
import io.github.gdrfgdrf.cutebedwars.abstracts.items.IItemProperties
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class SpecialItem(private val itemStack: ItemStack, properties: IItemProperties) : Item(
    itemStack,
    properties.apply {
        properties.droppable = false
    }
) {
    override fun give(player: Player, amount: Int, slotIndex: Int): ICommonItem {
        val givenItem = GivenItem(
            itemStack.clone(),
            this,
            player,
            amount
        )
        givenItem.give(slotIndex)

        return givenItem
    }
}