package io.github.gdrfgdrf.cutebedwars.abstracts.items

import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

interface IGivenItem {
    fun itemStack(): ItemStack
    fun item(): IItem
    fun player(): Player

    fun give()
    fun remove()
}