package io.github.gdrfgdrf.cutebedwars.events.listener

import io.github.gdrfgdrf.cutebedwars.abstracts.items.IItemCollections
import io.github.gdrfgdrf.cuteframework.bean.annotation.Component
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

@Component
class PlayerInteractEventListener : Listener {
    @EventHandler
    fun onInteractEvent(playerInteractEvent: PlayerInteractEvent) {
        val player = playerInteractEvent.player ?: return
        val itemStack = playerInteractEvent.item ?: return
        val action = playerInteractEvent.action ?: return

        val commonItem = IItemCollections.instance().find(player, itemStack) ?: return
        val properties = commonItem.properties

        if (action != Action.PHYSICAL) {
            properties.onClick?.invoke(playerInteractEvent, commonItem)
        }
        when (action) {
            Action.LEFT_CLICK_AIR -> properties.onLeftClick?.invoke(playerInteractEvent, commonItem)
            Action.LEFT_CLICK_BLOCK -> properties.onLeftClick?.invoke(playerInteractEvent, commonItem)
            Action.RIGHT_CLICK_AIR -> properties.onRightClick?.invoke(playerInteractEvent, commonItem)
            Action.RIGHT_CLICK_BLOCK -> properties.onRightClick?.invoke(playerInteractEvent, commonItem)
            Action.PHYSICAL -> {}
        }
    }
}