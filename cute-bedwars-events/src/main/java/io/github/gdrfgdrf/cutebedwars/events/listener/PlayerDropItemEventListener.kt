package io.github.gdrfgdrf.cutebedwars.events.listener

import io.github.gdrfgdrf.cutebedwars.abstracts.items.IGivenItems
import io.github.gdrfgdrf.cuteframework.bean.annotation.Component
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerDropItemEvent

@Component
class PlayerDropItemEventListener : Listener {
    @EventHandler
    fun onDropItem(playerDropItemEvent: PlayerDropItemEvent) {
        val player = playerDropItemEvent.player
        val itemDrop = playerDropItemEvent.itemDrop
        val itemStack = itemDrop.itemStack

        val givenItem = IGivenItems.instance().find(player, itemStack) ?: return
        givenItem.remove()
    }
}