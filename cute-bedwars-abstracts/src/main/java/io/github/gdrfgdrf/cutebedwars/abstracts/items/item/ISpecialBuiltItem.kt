package io.github.gdrfgdrf.cutebedwars.abstracts.items.item

import io.github.gdrfgdrf.cutebedwars.abstracts.items.given.ISpecialGivenItem
import org.bukkit.entity.Player

abstract class ISpecialBuiltItem : IItem() {
    abstract fun give(player: Player, slotIndex: Int = -1): ISpecialGivenItem
}