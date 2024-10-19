package io.github.gdrfgdrf.cutebedwars.items

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.concurrent.ConcurrentHashMap

object GivenItems {
    private val map = ConcurrentHashMap<Player, ConcurrentHashMap<ItemStack, GivenItem>>()

    fun find(player: Player, itemStack: ItemStack): GivenItem? {
        val map = get(player) ?: return null
        return map[itemStack]
    }

    fun get(player: Player): ConcurrentHashMap<ItemStack, GivenItem>? {
        return map[player]
    }

    fun contains(player: Player, itemStack: ItemStack): Boolean {
        val map = getOrCreate(player, false) ?: return false
        return map.containsKey(itemStack)
    }

    fun contains(player: Player, givenItem: GivenItem): Boolean {
        val map = getOrCreate(player, false) ?: return false
        return map.containsValue(givenItem)
    }

    fun add(player: Player, givenItem: GivenItem) {
        val map = getOrCreate(player)
        map!![givenItem.itemStack] = givenItem
    }

    fun remove(player: Player, givenItem: GivenItem) {
        val map = getOrCreate(player, false) ?: return
        map.remove(givenItem.itemStack)

        if (map.isEmpty()) {
            this.map.remove(player)
        }
    }

    fun removeAll(player: Player) {
        map.remove(player)
    }

    private fun getOrCreate(player: Player, createMap: Boolean = true): ConcurrentHashMap<ItemStack, GivenItem>? {
        if (!map.containsKey(player)) {
            if (!createMap) {
                return null
            }
            map[player] = ConcurrentHashMap<ItemStack, GivenItem>()
        }

        return map[player]
    }
}