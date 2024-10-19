package io.github.gdrfgdrf.cutebedwars.items

import io.github.gdrfgdrf.cutebedwars.abstracts.items.IGivenItem
import io.github.gdrfgdrf.cutebedwars.abstracts.items.IItem
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

class Item(
    private val itemStack: ItemStack,
    private val onClick: ((PlayerInteractEvent) -> Unit)? = null,
    private val onLeftClick: ((PlayerInteractEvent) -> Unit)? = null,
    private val onRightClick: ((PlayerInteractEvent) -> Unit)? = null,
) : IItem {
    override fun give(player: Player): IGivenItem {
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