package io.github.gdrfgdrf.cutebedwars.abstracts.items

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

@Service("item_collections")
@KotlinSingleton
interface IItemCollections {
    fun find(player: Player, providedItemStack: ItemStack): ICommonItem?
    fun get(player: Player): Map<ItemStack, ICommonItem>?
    fun contains(player: Player, itemStack: ItemStack): Boolean
    fun contains(player: Player, givenItem: ICommonItem): Boolean
    fun add(player: Player, givenItem: ICommonItem)
    fun remove(player: Player, givenItem: ICommonItem)
    fun removeAll(player: Player)

    companion object {
        fun instance(): IItemCollections = Mediator.get(IItemCollections::class.java)!!
    }
}