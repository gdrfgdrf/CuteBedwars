package io.github.gdrfgdrf.cutebedwars.events.listener.bukkit

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

class PlayerInteractEventListener : Listener {

    @EventHandler
    fun onInteractEvent(playerInteractEvent: PlayerInteractEvent) {
        val itemStack = playerInteractEvent.item

    }

}