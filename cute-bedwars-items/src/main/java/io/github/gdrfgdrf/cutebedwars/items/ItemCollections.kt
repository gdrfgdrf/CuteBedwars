package io.github.gdrfgdrf.cutebedwars.items

import de.tr7zw.changeme.nbtapi.NBT
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IItems
import io.github.gdrfgdrf.cutebedwars.abstracts.items.given.ISpecialGivenItem
import io.github.gdrfgdrf.cutebedwars.abstracts.items.IItemCollections
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.frequencyTask
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.uuid
import io.github.gdrfgdrf.cutebedwars.items.given.SpecialGivenItem
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.concurrent.ConcurrentHashMap

@ServiceImpl("item_collections")
object ItemCollections : IItemCollections {
    private val map = ConcurrentHashMap<String, LockItems>()

    override fun startPlayerInventoryScanning() {
        frequencyTask(100) {
            val players = Bukkit.getOnlinePlayers()
            players.forEach { player ->
                scanInventory(player)
            }
        }
    }

    override fun updateItemStack(player: Player, oldItemStack: ItemStack, newItemStack: ItemStack) {
        val map = get(player) ?: return
        val commonItem = find(player, oldItemStack) ?: return
        map.remove(oldItemStack)
        map[newItemStack] = commonItem
    }

    override fun find(player: Player, providedItemStack: ItemStack): ISpecialGivenItem? {
        val map = get(player) ?: return null
        map.forEach { (itemStack, commonItem) ->
            if (itemStack == providedItemStack) {
                return commonItem
            }
        }
        return null
    }

    override fun get(player: Player): MutableMap<ItemStack, ISpecialGivenItem>? {
        return map[player.uuid()]?.map()
    }

    override fun contains(player: Player, itemStack: ItemStack): Boolean {
        val lockItems = getOrCreate(player, false) ?: return false
        return lockItems.map().containsKey(itemStack)
    }

    override fun contains(player: Player, givenItem: ISpecialGivenItem): Boolean {
        val lockItems = getOrCreate(player, false) ?: return false
        return lockItems.map().containsValue(givenItem)
    }

    override fun add(player: Player, givenItem: ISpecialGivenItem) {
        val lockItems = getOrCreate(player)
        lockItems!!.map()[givenItem.itemStack] = givenItem
    }

    override fun remove(player: Player, givenItem: ISpecialGivenItem) {
        val lockItems = getOrCreate(player, false) ?: return
        lockItems.map().remove(givenItem.itemStack)

        if (lockItems.map().isEmpty()) {
            this.map.remove(player.uuid())
        }
    }

    override fun removeAll(player: Player) {
        map.remove(player.uuid())
    }

    override fun scanInventory(player: Player) {
        val lockItems = getOrCreate(player, true)!!
        val map = lockItems.map()
        lockItems.lockAndDo {
            it.clear()
        }

        lockItems.lock()

        val inventory = player.inventory
        inventory.forEach { itemStack ->
            itemStack ?: return@forEach

            val nbt = NBT.readNbt(itemStack)
            if (!nbt.hasTag("cute-bedwars-item-enum") ||
                !nbt.hasTag("cute-bedwars-item-special")) {
                return@forEach
            }

            val enumName = nbt.getString("cute-bedwars-item-enum")
            val special = nbt.getBoolean("cute-bedwars-item-special")
            if (!special) {
                return@forEach
            }

            val itemEnum = IItems.valueOf(enumName)
            val specialBuiltItem = itemEnum.special()

            val specialGivenItem = SpecialGivenItem(
                specialBuiltItem,
                itemStack,
                player
            )

            map[itemStack] = specialGivenItem
        }

        lockItems.unlock()

        if (map.isEmpty()) {
            this.map.remove(player.uuid())
        }
    }

    private fun getOrCreate(player: Player, createMap: Boolean = true): LockItems? {
        val uuid = player.uuid()

        if (!map.containsKey(uuid)) {
            if (!createMap) {
                return null
            }
            map[uuid] = LockItems.create()
        }

        return map[uuid]
    }

    class LockItems private constructor() {
        private val map = ConcurrentHashMap<ItemStack, ISpecialGivenItem>()
        private var locked: Boolean = false
        private val lock: Object = Object()

        fun map(): MutableMap<ItemStack, ISpecialGivenItem> {
            if (locked) {
                synchronized(lock) {
                    lock.wait()
                }
            }
            return map
        }

        fun lock() {
            locked = true
        }

        fun unlock() {
            locked = false
            synchronized(lock) {
                lock.notifyAll()
            }
        }

        fun lockAndDo(run: (MutableMap<ItemStack, ISpecialGivenItem>) -> Unit) {
            lock()
            run(map)
        }

        companion object {
            fun create() = LockItems()
        }
    }
}