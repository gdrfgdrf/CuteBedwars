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
    private val droppable: Boolean
) : IItem {
    override fun onClick(): ((PlayerInteractEvent) -> Unit)? = onClick
    override fun onLeftClick(): ((PlayerInteractEvent) -> Unit)? = onLeftClick
    override fun onRightClick(): ((PlayerInteractEvent) -> Unit)? = onRightClick
    override fun droppable(): Boolean = droppable

    override fun give(player: Player): IGivenItem {
        val givenItem = GivenItem(
            itemStack.clone(),
            this,
            player
        )
        givenItem.give()

        return givenItem
    }
}