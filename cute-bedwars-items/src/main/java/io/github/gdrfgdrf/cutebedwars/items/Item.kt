package io.github.gdrfgdrf.cutebedwars.items

import io.github.gdrfgdrf.cutebedwars.abstracts.items.ICommonItem
import io.github.gdrfgdrf.cutebedwars.abstracts.items.IItem
import io.github.gdrfgdrf.cutebedwars.abstracts.items.IItemProperties
import io.github.gdrfgdrf.cutebedwars.items.item.CommonItem
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

open class Item(
    private val itemStack: ItemStack,
    override val properties: IItemProperties
) : IItem {
    init {
        properties.droppable = true
    }

    override fun give(player: Player, amount: Int, slotIndex: Int): ICommonItem {
        val commonItem = CommonItem(
            itemStack.clone(),
            this,
            player,
            amount
        )
        commonItem.give(slotIndex)

        return commonItem
    }
}