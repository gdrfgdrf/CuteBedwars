package io.github.gdrfgdrf.cutebedwars.abstracts.items.item

import io.github.gdrfgdrf.cutebedwars.abstracts.items.property.IItemProperties
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

abstract class IItem {
    abstract val itemStack: ItemStack
    abstract val properties: IItemProperties
    private var appliedName = false

    fun applyName() {
        if (!appliedName) {
            properties.applyTo(itemStack)
            appliedName = true
        }
    }

    fun has(player: Player): Boolean {
        player.inventory.forEach { itemStack ->
            itemStack ?: return@forEach
            if (itemStack == this.itemStack) {
                return true
            }
        }
        return false
    }
}