package io.github.gdrfgdrf.cutebedwars.items.item

import io.github.gdrfgdrf.cutebedwars.abstracts.items.item.IBuiltItem
import io.github.gdrfgdrf.cutebedwars.abstracts.items.property.IItemProperties
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

open class BuiltItem(
    override val itemStack: ItemStack,
    override val properties: IItemProperties
) : IBuiltItem() {
    override fun give(player: Player, amount: Int, slotIndex: Int) {
        applyName()
        if (slotIndex == -1) {
            player.inventory.addItem(itemStack.clone())
        } else {
            player.inventory.setItem(slotIndex, itemStack.clone())
        }
    }
}