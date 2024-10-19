package io.github.gdrfgdrf.cutebedwars.abstracts.items

import org.bukkit.entity.Player

interface IItem {
    fun give(player: Player): IGivenItem
}