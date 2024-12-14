package io.github.gdrfgdrf.cutebedwars.abstracts.items.given

import io.github.gdrfgdrf.cutebedwars.abstracts.items.property.IItemProperties
import io.github.gdrfgdrf.cutebedwars.abstracts.items.item.IBuiltItem
import io.github.gdrfgdrf.cutebedwars.abstracts.items.item.ISpecialBuiltItem
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * 仅特殊物品拥有 GivenItem
 */
interface ISpecialGivenItem {
    val item: ISpecialBuiltItem
    var itemStack: ItemStack
    val player: Player
    val properties: IItemProperties

    fun update()
    fun give(slotIndex: Int)
    fun remove()

    fun slot(): Int
}