package io.github.gdrfgdrf.cutebedwars.items

import io.github.gdrfgdrf.cutebedwars.abstracts.items.IItemBuilder
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import org.bukkit.Material
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

@ServiceImpl("item_builder")
class ItemBuilder : IItemBuilder {
    private var material: Material? = null
    private var name: (() -> LanguageString)? = null
    private val lores = arrayListOf<String>()
    private var onClick: ((PlayerInteractEvent) -> Unit)? = null
    private var onLeftClick: ((PlayerInteractEvent) -> Unit)? = null
    private var onRightClick: ((PlayerInteractEvent) -> Unit)? = null

    override fun material(): Material? = material
    override fun name(): (() -> LanguageString)? = name
    override fun lores(): MutableList<String> = lores
    override fun onClick(): ((PlayerInteractEvent) -> Unit)? = onClick
    override fun onLeftClick(): ((PlayerInteractEvent) -> Unit)? = onLeftClick
    override fun onRightClick(): ((PlayerInteractEvent) -> Unit)? = onRightClick

    override fun material(material: Material): ItemBuilder {
        this.material = material
        return this
    }

    override fun name(string: () -> LanguageString): ItemBuilder {
        this.name = string
        return this
    }

    override fun addLore(string: String): ItemBuilder {
        lores.add(string)
        return this
    }

    override fun removeLore(string: String): ItemBuilder {
        lores.remove(string)
        return this
    }

    override fun removeLore(index: Int): ItemBuilder {
        lores.removeAt(index)
        return this
    }

    override fun onClick(onClick: (PlayerInteractEvent) -> Unit): ItemBuilder {
        this.onClick = onClick
        return this
    }

    override fun onLeftClick(onLeftClick: (PlayerInteractEvent) -> Unit): ItemBuilder {
        this.onLeftClick = onLeftClick
        return this
    }

    override fun onRightClick(onRightClick: (PlayerInteractEvent) -> Unit): ItemBuilder {
        this.onRightClick = onRightClick
        return this
    }

    override fun build(): Item {
        if (material == null) {
            throw IllegalArgumentException("material is required")
        }
        val itemStack = ItemStack(material)
        val itemMeta = itemStack.itemMeta

        name?.let {
            itemMeta.displayName = it().get().string
        }
        itemMeta.lore = arrayListOf()
        itemMeta.lore.addAll(lores)

        itemStack.itemMeta = itemMeta

        val item = Item(itemStack, onClick, onLeftClick, onRightClick)
        return item
    }
}