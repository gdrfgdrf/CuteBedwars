package io.github.gdrfgdrf.cutebedwars.items

import io.github.gdrfgdrf.cutebedwars.abstracts.items.IGivenItem
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

class GivenItem(
    val itemStack: ItemStack,
    private val onClick: ((PlayerInteractEvent) -> Unit)? = null,
    private val onLeftClick: ((PlayerInteractEvent) -> Unit)? = null,
    private val onRightClick: ((PlayerInteractEvent) -> Unit)? = null,
    private val player: Player
): IGivenItem {
    override fun itemStack(): ItemStack = itemStack
    override fun onClick(): ((PlayerInteractEvent) -> Unit)? = onClick
    override fun onLeftClick(): ((PlayerInteractEvent) -> Unit)? = onLeftClick
    override fun onRightClick(): ((PlayerInteractEvent) -> Unit)? = onRightClick
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