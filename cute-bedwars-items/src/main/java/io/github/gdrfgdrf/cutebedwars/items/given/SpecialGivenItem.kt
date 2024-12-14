package io.github.gdrfgdrf.cutebedwars.items.given

import io.github.gdrfgdrf.cutebedwars.abstracts.items.given.ISpecialGivenItem
import io.github.gdrfgdrf.cutebedwars.abstracts.items.item.ISpecialBuiltItem
import io.github.gdrfgdrf.cutebedwars.items.ItemCollections
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

open class SpecialGivenItem(
    override val item: ISpecialBuiltItem,
    override var itemStack: ItemStack,
    override val player: Player,
) : ISpecialGivenItem {
    override val properties = item.properties.copy()

    override fun update() {
        // 在玩家背包中寻找给予出去的物品的物品槽
        val slot = slot()
        itemStack = player.inventory.getItem(slot)

        // 重新生成新的物品
        val newItemStack = properties.generate()
        ItemCollections.updateItemStack(player, itemStack, newItemStack)

        // 更新 itemStack 为新生成的物品
        itemStack = newItemStack

        // 设置到玩家背包
        player.inventory.setItem(slot, newItemStack)
    }

    override fun give(slotIndex: Int) {
        // 检查玩家背包有没有当前物品
        val slot = slot()
        if (slot != -1) {
            return
        }

        if (slotIndex == -1) {
            player.inventory.addItem(itemStack)
        } else {
            player.inventory.setItem(slotIndex, itemStack)
        }
    }

    override fun remove() {
        ItemCollections.remove(player, this)

        val slot = slot()
        if (slot != -1) {
            player.inventory.removeItem(itemStack)
        } else {
            if (player.itemOnCursor == itemStack) {
                player.itemOnCursor = null
            }
        }
    }

    override fun slot(): Int {
        player.inventory.forEachIndexed { index, itemStack ->
            itemStack ?: return@forEachIndexed
            if (itemStack == this@SpecialGivenItem.itemStack) {
                return index
            }
        }

        return -1
    }
}