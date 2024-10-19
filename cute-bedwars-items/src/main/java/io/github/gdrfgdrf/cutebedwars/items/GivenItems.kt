package io.github.gdrfgdrf.cutebedwars.items

import io.github.gdrfgdrf.cutebedwars.abstracts.items.IGivenItem
import io.github.gdrfgdrf.cutebedwars.abstracts.items.IGivenItems
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.concurrent.ConcurrentHashMap

@ServiceImpl("given_items")
object GivenItems : IGivenItems {
    private val map = ConcurrentHashMap<Player, ConcurrentHashMap<ItemStack, IGivenItem>>()

    override fun find(player: Player, itemStack: ItemStack): IGivenItem? {
        val map = get(player) ?: return null
        return map[itemStack]
    }

    override fun get(player: Player): Map<ItemStack, IGivenItem>? {
        return map[player]
    }

    override fun contains(player: Player, itemStack: ItemStack): Boolean {
        val map = getOrCreate(player, false) ?: return false
        return map.containsKey(itemStack)
    }

    override fun contains(player: Player, givenItem: IGivenItem): Boolean {
        val map = getOrCreate(player, false) ?: return false
        return map.containsValue(givenItem)
    }

    override fun add(player: Player, givenItem: IGivenItem) {
        val map = getOrCreate(player)
        map!![givenItem.itemStack()] = givenItem
    }

    override fun remove(player: Player, givenItem: IGivenItem) {
        val map = getOrCreate(player, false) ?: return
        map.remove(givenItem.itemStack())

        if (map.isEmpty()) {
            this.map.remove(player)
        }
    }

    override fun removeAll(player: Player) {
        map.remove(player)
    }

    private fun getOrCreate(player: Player, createMap: Boolean = true): MutableMap<ItemStack, IGivenItem>? {
        if (!map.containsKey(player)) {
            if (!createMap) {
                return null
            }
            map[player] = ConcurrentHashMap<ItemStack, IGivenItem>()
        }

        return map[player]
    }
}