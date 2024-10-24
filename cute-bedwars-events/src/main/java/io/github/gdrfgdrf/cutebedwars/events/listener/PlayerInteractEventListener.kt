package io.github.gdrfgdrf.cutebedwars.events.listener

import io.github.gdrfgdrf.cutebedwars.abstracts.items.IGivenItems
import io.github.gdrfgdrf.cuteframework.bean.annotation.Component
import org.bukkit.Bukkit
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

        val givenItem = IGivenItems.instance().find(player, itemStack) ?: return
        val item = givenItem.item()

        if (action != Action.PHYSICAL) {
            item.onClick()?.invoke(playerInteractEvent)
        }
        when (action) {
            Action.LEFT_CLICK_AIR -> item.onLeftClick()?.invoke(playerInteractEvent)
            Action.LEFT_CLICK_BLOCK -> item.onLeftClick()?.invoke(playerInteractEvent)
            Action.RIGHT_CLICK_AIR -> item.onRightClick()?.invoke(playerInteractEvent)
            Action.RIGHT_CLICK_BLOCK -> item.onRightClick()?.invoke(playerInteractEvent)
            Action.PHYSICAL -> {}
        }
    }
}