package io.github.gdrfgdrf.cutebedwars.abstracts.items.item

import org.bukkit.entity.Player

abstract class IBuiltItem : IItem() {
    abstract fun give(player: Player, amount: Int = 1, slotIndex: Int = -1)
}