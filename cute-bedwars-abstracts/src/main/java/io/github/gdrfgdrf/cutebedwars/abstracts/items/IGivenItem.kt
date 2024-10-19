package io.github.gdrfgdrf.cutebedwars.abstracts.items

import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

interface IGivenItem {
    fun itemStack(): ItemStack
    fun onClick(): ((PlayerInteractEvent) -> Unit)?
    fun onLeftClick(): ((PlayerInteractEvent) -> Unit)?
    fun onRightClick(): ((PlayerInteractEvent) -> Unit)?
    fun player(): Player

    fun give()
    fun remove()
}