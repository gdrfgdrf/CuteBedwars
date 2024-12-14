package io.github.gdrfgdrf.cutebedwars.items.item

import de.tr7zw.changeme.nbtapi.NBT
import io.github.gdrfgdrf.cutebedwars.abstracts.items.given.ISpecialGivenItem
import io.github.gdrfgdrf.cutebedwars.abstracts.items.property.IItemProperties
import io.github.gdrfgdrf.cutebedwars.abstracts.items.item.ISpecialBuiltItem
import io.github.gdrfgdrf.cutebedwars.items.given.SpecialGivenItem
import io.github.gdrfgdrf.cutebedwars.items.ItemCollections
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class SpecialBuiltItem(
    override val itemStack: ItemStack,
    override val properties: IItemProperties
) : ISpecialBuiltItem() {
    override fun give(player: Player, slotIndex: Int): ISpecialGivenItem {
        applyName()

        val specialGivenItem = SpecialGivenItem(
            this,
            itemStack.clone(),
            player,
        )
        if (!has(player)) {
            ItemCollections.add(player, specialGivenItem)
            specialGivenItem.give(slotIndex)
            properties.onGiven?.invoke(player, specialGivenItem)
        }

        return specialGivenItem
    }
}