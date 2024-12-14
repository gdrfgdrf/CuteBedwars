package io.github.gdrfgdrf.cutebedwars.abstracts.items

import io.github.gdrfgdrf.cutebedwars.abstracts.items.given.ISpecialGivenItem
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

@Service("item_collections")
@KotlinSingleton
interface IItemCollections {
    fun startPlayerInventoryScanning()

    fun updateItemStack(player: Player, oldItemStack: ItemStack, newItemStack: ItemStack)
    fun find(player: Player, providedItemStack: ItemStack): ISpecialGivenItem?
    fun get(player: Player): Map<ItemStack, ISpecialGivenItem>?
    fun contains(player: Player, itemStack: ItemStack): Boolean
    fun contains(player: Player, givenItem: ISpecialGivenItem): Boolean
    fun add(player: Player, givenItem: ISpecialGivenItem)
    fun remove(player: Player, givenItem: ISpecialGivenItem)
    fun removeAll(player: Player)

    /**
     * 扫描玩家背包获取所有特殊物品以重新设置 map
     */
    fun scanInventory(player: Player)

    companion object {
        fun instance(): IItemCollections = Mediator.get(IItemCollections::class.java)!!
    }
}