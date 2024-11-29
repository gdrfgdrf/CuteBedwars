package io.github.gdrfgdrf.cutebedwars.items.item

import de.tr7zw.changeme.nbtapi.NBT
import io.github.gdrfgdrf.cutebedwars.abstracts.items.ICommonItem
import io.github.gdrfgdrf.cutebedwars.abstracts.items.IItem
import io.github.gdrfgdrf.cutebedwars.items.ItemCollections
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

open class CommonItem(
    override var itemStack: ItemStack,
    override val item: IItem,
    override val player: Player,
    override var amount: Int
) : ICommonItem {
    override val properties = item.properties.copy()
    override val id: Long = getIdFromNbt()

    init {
        itemStack.amount = amount
    }

    override fun update() {
        if (item !is SpecialItem) {
            throw IllegalArgumentException("only the special item can be updated")
        }
        if (properties.droppable) {
            throw IllegalArgumentException("only the undroppable item can be updated")
        }

        val slot = slot()
        itemStack = player.inventory.getItem(slot)

        val newItemStack = properties.generate()
        ItemCollections.replaceKey(player, itemStack, newItemStack)

        itemStack = newItemStack

        player.inventory.setItem(slot, newItemStack)
    }

    override fun give(slotIndex: Int) {
        ItemCollections.add(player, this)
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
        player.inventory.forEachIndexed { index2, itemStack ->
            itemStack ?: return@forEachIndexed
            if (itemStack.equals(this@CommonItem.itemStack)) {
                val nbt = NBT.readNbt(itemStack)
                val id = nbt.getLong("cute-bedwars-item")
                if (this@CommonItem.id == id) {
                    return index2
                }
            }
        }

        return -1
    }

    private fun getIdFromNbt(): Long {
        val nbt = NBT.readNbt(itemStack)
        val id = nbt.getLong("cute-bedwars-item")
            ?: throw IllegalArgumentException("this item stack's nbt does not have cute-bedwars-item")
        return id
    }
}