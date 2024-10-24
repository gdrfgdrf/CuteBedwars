package io.github.gdrfgdrf.cutebedwars.items

import io.github.gdrfgdrf.cutebedwars.abstracts.items.IGivenItem
import io.github.gdrfgdrf.cutebedwars.abstracts.items.IItem
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

class GivenItem(
    private val itemStack: ItemStack,
    private val item: IItem,
    private val player: Player
): IGivenItem {
    override fun itemStack(): ItemStack = itemStack
    override fun item(): IItem = item
    override fun player(): Player = player

    override fun give() {
        GivenItems.add(player, this)
        player.inventory.addItem(itemStack)
    }

    override fun remove() {
        GivenItems.remove(player, this)
        player.inventory.removeItem(itemStack)
    }
}