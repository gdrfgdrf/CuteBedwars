package io.github.gdrfgdrf.cutebedwars.items

import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

class GivenItem(
    val itemStack: ItemStack,
    val onClick: ((PlayerInteractEvent) -> Unit)? = null,
    val onLeftClick: ((PlayerInteractEvent) -> Unit)? = null,
    val onRightClick: ((PlayerInteractEvent) -> Unit)? = null,
    val player: Player
) {
    fun give() {
        GivenItems.add(player, this)
        player.inventory.addItem(itemStack)
    }

    fun remove() {
        GivenItems.remove(player, this)
        player.inventory.removeItem(itemStack)
    }
}