package io.github.gdrfgdrf.cutebedwars.events.listener

import io.github.gdrfgdrf.cutebedwars.abstracts.items.IGivenItems
import io.github.gdrfgdrf.cuteframework.bean.annotation.Component
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

@Component
class PlayerInteractEventListener : Listener {
    @EventHandler
    fun onInteractEvent(playerInteractEvent: PlayerInteractEvent) {
        val player = playerInteractEvent.player
        val itemStack = playerInteractEvent.item
        val action = playerInteractEvent.action ?: return

        val givenItem = IGivenItems.instance().find(player, itemStack) ?: return
        when (action) {
            Action.LEFT_CLICK_AIR -> givenItem.onLeftClick()
            Action.LEFT_CLICK_BLOCK -> givenItem.onLeftClick()
            Action.RIGHT_CLICK_AIR -> givenItem.onRightClick()
            Action.RIGHT_CLICK_BLOCK -> givenItem.onRightClick()
            Action.PHYSICAL -> {}
        }
        if (action != Action.PHYSICAL) {
            givenItem.onClick()
        }
    }
}