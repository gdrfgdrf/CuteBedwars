package io.github.gdrfgdrf.cutebedwars.items

import de.tr7zw.nbtapi.NBT
import de.tr7zw.nbtapi.NBTCompoundList
import de.tr7zw.nbtapi.NBTItem
import de.tr7zw.nbtapi.NBTList
import de.tr7zw.nbtapi.NBTListCompound
import de.tr7zw.nbtapi.iface.ReadWriteItemNBT
import io.github.gdrfgdrf.cutebedwars.abstracts.items.IItem
import io.github.gdrfgdrf.cutebedwars.abstracts.items.IItemBuilder
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import org.bukkit.Material
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

@ServiceImpl("item_builder")
class ItemBuilder : IItemBuilder {
    private var material: Material? = null
    private var name: (() -> LanguageString)? = null
    private val lores = arrayListOf<String>()
    private val flags = arrayListOf<ItemFlag>()
    private var onClick: ((PlayerInteractEvent) -> Unit)? = null
    private var onLeftClick: ((PlayerInteractEvent) -> Unit)? = null
    private var onRightClick: ((PlayerInteractEvent) -> Unit)? = null
    private var unbreakable: Boolean = false
    private val canPlaceOn = arrayListOf<Material>()
    private val canDestroy = arrayListOf<Material>()
    private var droppable: Boolean = false

    override fun material(): Material? = material
    override fun name(): (() -> LanguageString)? = name
    override fun lores(): MutableList<String> = lores
    override fun flags(): MutableList<ItemFlag> = flags
    override fun onClick(): ((PlayerInteractEvent) -> Unit)? = onClick
    override fun onLeftClick(): ((PlayerInteractEvent) -> Unit)? = onLeftClick
    override fun onRightClick(): ((PlayerInteractEvent) -> Unit)? = onRightClick
    override fun unbreakable(): Boolean = unbreakable
    override fun canPlaceOn(): MutableList<Material> = canPlaceOn
    override fun canDestroy(): MutableList<Material> = canDestroy
    override fun droppable(): Boolean = droppable

    override fun material(material: Material): IItemBuilder {
        this.material = material
        return this
    }

    override fun name(string: () -> LanguageString): IItemBuilder {
        this.name = string
        return this
    }

    override fun lore(string: String): IItemBuilder {
        lores.add(string)
        return this
    }

    override fun removeLore(string: String): IItemBuilder {
        lores.remove(string)
        return this
    }

    override fun removeLore(index: Int): IItemBuilder {
        lores.removeAt(index)
        return this
    }

    override fun flag(vararg flag: ItemFlag): IItemBuilder {
        flags.addAll(flag)
        return this
    }

    override fun removeFlag(vararg flag: ItemFlag): IItemBuilder {
        flags.removeAll(flag.toSet())
        return this
    }

    override fun onClick(onClick: (PlayerInteractEvent) -> Unit): IItemBuilder {
        this.onClick = onClick
        return this
    }

    override fun onLeftClick(onLeftClick: (PlayerInteractEvent) -> Unit): IItemBuilder {
        this.onLeftClick = onLeftClick
        return this
    }

    override fun onRightClick(onRightClick: (PlayerInteractEvent) -> Unit): IItemBuilder {
        this.onRightClick = onRightClick
        return this
    }

    override fun unbreakable(unbreakable: Boolean): IItemBuilder {
        this.unbreakable = unbreakable
        return this
    }

    override fun canPlaceOn(vararg material: Material): IItemBuilder {
        this.canPlaceOn.addAll(material)
        return this
    }

    override fun removeCanPlaceOn(vararg material: Material): IItemBuilder {
        this.canPlaceOn.removeAll(material.toSet())
        return this
    }

    override fun canDestroy(vararg material: Material): IItemBuilder {
        this.canDestroy.addAll(material)
        return this
    }

    override fun removeCanDestroy(vararg material: Material): IItemBuilder {
        this.canDestroy.removeAll(material.toSet())
        return this
    }

    override fun droppable(droppable: Boolean): IItemBuilder {
        this.droppable = droppable
        return this
    }

    override fun build(nbtModifier: ((ReadWriteItemNBT) -> Unit)?): IItem {
        if (material == null) {
            throw IllegalArgumentException("material is required")
        }
        val itemStack = ItemStack(material)
        val itemMeta = itemStack.itemMeta

        name?.let {
            itemMeta.displayName = it().get().string
        }
        itemMeta.lore = lores
        itemMeta.addItemFlags(*flags.toTypedArray())
        if (unbreakable) {
            itemMeta.isUnbreakable = true
        }

        itemStack.itemMeta = itemMeta

        NBT.modify(itemStack) { operableNbt ->
            val canPlaceOnList = operableNbt.getStringList("CanPlaceOn")
            canPlaceOn.forEach { canPlaceOnMaterial ->
                canPlaceOnList.add("minecraft:${canPlaceOnMaterial.name.lowercase()}")
            }

            val canDestroyList = operableNbt.getStringList("CanDestroy")
            canDestroy.forEach { canDestroyMaterial ->
                canDestroyList.add("minecraft:${canDestroyMaterial.name.lowercase()}")
            }
        }
        if (nbtModifier != null) {
            NBT.modify(itemStack, nbtModifier)
        }

        val item = Item(itemStack, onClick, onLeftClick, onRightClick, droppable)
        return item
    }
}