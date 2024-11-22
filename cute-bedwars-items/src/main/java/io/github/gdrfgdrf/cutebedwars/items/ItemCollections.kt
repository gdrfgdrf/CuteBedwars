package io.github.gdrfgdrf.cutebedwars.items

import io.github.gdrfgdrf.cutebedwars.abstracts.items.ICommonItem
import io.github.gdrfgdrf.cutebedwars.abstracts.items.IItemCollections
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.uuid
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.concurrent.ConcurrentHashMap

@ServiceImpl("item_collections")
object ItemCollections : IItemCollections {
    private val map = ConcurrentHashMap<String, ConcurrentHashMap<ItemStack, ICommonItem>>()

    override fun replaceKey(player: Player, oldItemStack: ItemStack, newItemStack: ItemStack) {
        val map = get(player) ?: return
        val commonItem = find(player, oldItemStack) ?: return
        map.remove(oldItemStack)
        map[newItemStack] = commonItem
    }

    override fun find(player: Player, providedItemStack: ItemStack): ICommonItem? {
        val map = get(player) ?: return null
        map.forEach { (itemStack, commonItem) ->
            if (itemStack == providedItemStack) {
                return commonItem
            }
        }
        return null
    }

    override fun get(player: Player): MutableMap<ItemStack, ICommonItem>? {
        return map[player.uuid()]
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
            this.map.remove(player.uuid())
        }
    }

    override fun removeAll(player: Player) {
        map.remove(player.uuid())
    }

    private fun getOrCreate(player: Player, createMap: Boolean = true): MutableMap<ItemStack, ICommonItem>? {
        val uuid = player.uuid()

        if (!map.containsKey(uuid)) {
            if (!createMap) {
                return null
            }
            map[uuid] = ConcurrentHashMap<ItemStack, ICommonItem>()
        }

        return map[uuid]
    }
}