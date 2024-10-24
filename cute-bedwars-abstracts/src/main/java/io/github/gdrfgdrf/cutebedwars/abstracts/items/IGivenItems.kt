package io.github.gdrfgdrf.cutebedwars.abstracts.items

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.concurrent.ConcurrentHashMap

@Service("given_items")
@KotlinSingleton
interface IGivenItems {
    fun find(player: Player, itemStack: ItemStack): IGivenItem?
    fun get(player: Player): Map<ItemStack, IGivenItem>?
    fun contains(player: Player, itemStack: ItemStack): Boolean
    fun contains(player: Player, givenItem: IGivenItem): Boolean
    fun add(player: Player, givenItem: IGivenItem)
    fun remove(player: Player, givenItem: IGivenItem)
    fun removeAll(player: Player)

    companion object {
        fun instance(): IGivenItems = Mediator.get(IGivenItems::class.java)!!
    }
}