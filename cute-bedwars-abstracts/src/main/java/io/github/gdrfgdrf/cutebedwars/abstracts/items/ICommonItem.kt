package io.github.gdrfgdrf.cutebedwars.abstracts.items

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

interface ICommonItem {
    var itemStack: ItemStack
    val item: IItem
    val player: Player
    val properties: IItemProperties
    var amount: Int

    val id: Long

    fun update()
    fun give(slotIndex: Int)
    fun remove()

    fun slot(): Int
}