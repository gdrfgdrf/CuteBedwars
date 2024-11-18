package io.github.gdrfgdrf.cutebedwars.events.listener

import io.github.gdrfgdrf.cutebedwars.abstracts.selection.ISelections
import io.github.gdrfgdrf.cuteframework.bean.annotation.Component
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerChangedWorldEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

@Component
class PlayerEventListener : Listener {
    @EventHandler
    fun onPlayerJoin(playerJoinEvent: PlayerJoinEvent) {
        val player = playerJoinEvent.player
        ISelections.instance().remove(player)
    }

    @EventHandler
    fun onPlayerQuit(playerQuitEvent: PlayerQuitEvent) {
        val player = playerQuitEvent.player
        ISelections.instance().remove(player)
    }

    @EventHandler
    fun onPlayerChangedWorld(playerChangeWorldEvent: PlayerChangedWorldEvent) {
        val player = playerChangeWorldEvent.player
        ISelections.instance().remove(player)
    }
}