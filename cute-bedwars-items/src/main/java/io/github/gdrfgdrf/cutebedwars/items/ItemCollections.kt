package io.github.gdrfgdrf.cutebedwars.items

import io.github.gdrfgdrf.cutebedwars.abstracts.items.ICommonItem
import io.github.gdrfgdrf.cutebedwars.abstracts.items.IItemCollections
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.concurrent.ConcurrentHashMap

@ServiceImpl("item_collections")
object ItemCollections : IItemCollections {
    private val map = ConcurrentHashMap<Player, ConcurrentHashMap<ItemStack, ICommonItem>>()

    override fun find(player: Player, itemStack: ItemStack): ICommonItem? {
        val map = get(player) ?: return null
        return map[itemStack]
    }

    override fun get(player: Player): Map<ItemStack, ICommonItem>? {
        return map[player]
    }

    override fun contains(player: Player, itemStack: ItemStack): Boolean {
        val map = getOrCreate(player, false) ?: return false
        return map.containsKey(itemStack)
    }

    override fun contains(player: Player, givenItem: ICommonItem): Boolean {
        val map = getOrCreate(player, false) ?: return false
        return map.containsValue(givenItem)
    }

    override fun add(player: Player, givenItem: ICommonItem) {
        val map = getOrCreate(player)
        map!![givenItem.itemStack] = givenItem
    }

    override fun remove(player: Player, givenItem: ICommonItem) {
        val map = getOrCreate(player, false) ?: return
        map.remove(givenItem.itemStack)

        if (map.isEmpty()) {
            this.map.remove(player)
        }
    }

    override fun removeAll(player: Player) {
        map.remove(player)
    }

    private fun getOrCreate(player: Player, createMap: Boolean = true): MutableMap<ItemStack, ICommonItem>? {
        if (!map.containsKey(player)) {
            if (!createMap) {
                return null
            }
            map[player] = ConcurrentHashMap<ItemStack, ICommonItem>()
        }

        return map[player]
    }
}