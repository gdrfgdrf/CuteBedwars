package io.github.gdrfgdrf.cutebedwars.events.listener

import io.github.gdrfgdrf.cutebedwars.abstracts.items.IItemCollections
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class PlayerInventoryClickEventListener : Listener {

    @EventHandler
    fun onInventoryClick(inventoryClickEvent: InventoryClickEvent) {
        val player = inventoryClickEvent.view.player ?: return
        if (player !is Player) {
            return
        }
        val currentItem = inventoryClickEvent.currentItem ?: return

        val commonItem = IItemCollections.instance().find(player, currentItem) ?: return
        if (!commonItem.properties.movable) {
            inventoryClickEvent.isCancelled = true
        }
    }

}