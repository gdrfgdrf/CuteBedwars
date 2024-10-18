package io.github.gdrfgdrf.cutebedwars.items

import org.bukkit.Material
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

class ItemBuilder {
    var material: Material? = null
        private set

    var name: String? = null
        private set
    val lores = arrayListOf<String>()
    var onClick: ((PlayerInteractEvent) -> Unit)? = null
        private set
    var onLeftClick: ((PlayerInteractEvent) -> Unit)? = null
        private set
    var onRightClick: ((PlayerInteractEvent) -> Unit)? = null
        private set

    fun material(material: Material): ItemBuilder {
        this.material = material
        return this
    }

    fun name(name: String): ItemBuilder {
        this.name = name
        return this
    }

    fun addLore(string: String): ItemBuilder {
        lores.add(string)
        return this
    }

    fun removeLore(string: String): ItemBuilder {
        lores.remove(string)
        return this
    }

    fun removeLore(index: Int): ItemBuilder {
        lores.removeAt(index)
        return this
    }

    fun onClick(onClick: (PlayerInteractEvent) -> Unit): ItemBuilder {
        this.onClick = onClick
        return this
    }

    fun onLeftClick(onLeftClick: (PlayerInteractEvent) -> Unit): ItemBuilder {
        this.onLeftClick = onLeftClick
        return this
    }

    fun onRightClick(onRightClick: (PlayerInteractEvent) -> Unit): ItemBuilder {
        this.onRightClick = onRightClick
        return this
    }

    fun build(): Item {
        if (material == null) {
            throw IllegalArgumentException("material is required")
        }
        val itemStack = ItemStack(material)
        val itemMeta = itemStack.itemMeta

        itemMeta.displayName = name
        itemMeta.lore.addAll(lores)

        itemStack.itemMeta = itemMeta

        val item = Item(itemStack, onClick, onLeftClick, onRightClick)
        return item
    }
}