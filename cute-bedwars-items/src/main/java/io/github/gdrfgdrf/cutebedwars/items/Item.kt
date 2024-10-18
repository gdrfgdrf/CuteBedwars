package io.github.gdrfgdrf.cutebedwars.items

import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

class Item(
    private val itemStack: ItemStack,
    val onClick: ((PlayerInteractEvent) -> Unit)? = null,
    val onLeftClick: ((PlayerInteractEvent) -> Unit)? = null,
    val onRightClick: ((PlayerInteractEvent) -> Unit)? = null,
) {
    fun give(player: Player): GivenItem {
        val givenItem = GivenItem(
            itemStack.clone(),
            onClick,
            onLeftClick,
            onRightClick,
            player
        )
        givenItem.give()

        return givenItem
    }
}