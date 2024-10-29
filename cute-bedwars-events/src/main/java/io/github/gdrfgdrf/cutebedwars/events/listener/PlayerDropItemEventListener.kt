package io.github.gdrfgdrf.cutebedwars.events.listener

import io.github.gdrfgdrf.cutebedwars.abstracts.items.IItemCollections
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

        val commonItem = IItemCollections.instance().find(player, itemStack) ?: return
        val properties = commonItem.properties

        if (!properties.droppable) {
            playerDropItemEvent.isCancelled = true
        }
    }
}