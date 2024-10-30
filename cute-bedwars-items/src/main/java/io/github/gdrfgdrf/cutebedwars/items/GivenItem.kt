package io.github.gdrfgdrf.cutebedwars.items

import io.github.gdrfgdrf.cutebedwars.items.item.CommonItem
import io.github.gdrfgdrf.cutebedwars.items.item.SpecialItem
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class GivenItem(
    itemStack: ItemStack,
    item: SpecialItem,
    player: Player,
    amount: Int
) : CommonItem(itemStack, item, player, amount) {



}