package io.github.gdrfgdrf.cutebedwars.abstracts.items

import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerInteractEvent

interface IItem {
    fun onClick(): ((PlayerInteractEvent) -> Unit)?
    fun onLeftClick(): ((PlayerInteractEvent) -> Unit)?
    fun onRightClick(): ((PlayerInteractEvent) -> Unit)?
    fun droppable(): Boolean
    fun give(player: Player): IGivenItem
}