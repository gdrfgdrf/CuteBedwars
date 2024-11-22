package io.github.gdrfgdrf.cutebedwars.items.property

import com.github.yitter.idgen.YitIdHelper
import de.tr7zw.nbtapi.NBT
import io.github.gdrfgdrf.cutebedwars.abstracts.items.ICommonItem
import io.github.gdrfgdrf.cutebedwars.abstracts.items.IItemProperties
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.ICustomList
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.customList
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.replaceToColorSymbol
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

@ServiceImpl("item_properties")
class ItemProperties : IItemProperties {
    override var material: Material? = null
    override var name: (() -> LanguageString)? = null

    override val lores: ICustomList<String> = customList()
    override val flags: ICustomList<ItemFlag> = customList()

    override var onGiven: ((Player, ICommonItem) -> Unit)? = null
    override var onClick: ((PlayerInteractEvent, ICommonItem) -> Unit)? = null
    override var onLeftClick: ((PlayerInteractEvent, ICommonItem) -> Unit)? = null
    override var onRightClick: ((PlayerInteractEvent, ICommonItem) -> Unit)? = null

    override var unbreakable: Boolean = false
    override var droppable: Boolean = true
    override var movable: Boolean = true
    override val canPlaceOn: ICustomList<Material> = customList()
    override val canDestroy: ICustomList<Material> = customList()

    override fun check() {
        if (material == null) {
            throw IllegalArgumentException("material is required")
        }
    }

    override fun copy(): IItemProperties {
        val properties = ItemProperties()
        properties.material = this.material
        properties.name = this.name

        properties.lores.add(*this.lores.list.toTypedArray())
        properties.flags.add(*this.flags.list.toTypedArray())

        properties.onGiven = this.onGiven
        properties.onClick = this.onClick
        properties.onLeftClick = this.onLeftClick
        properties.onRightClick = this.onRightClick

        properties.unbreakable = this.unbreakable
        properties.droppable = this.droppable
        properties.canPlaceOn.add(*this.canPlaceOn.list.toTypedArray())
        properties.canDestroy.add(*this.canDestroy.list.toTypedArray())
        return properties
    }

    override fun generate(withName: Boolean): ItemStack {
        val itemStack = ItemStack(material)
        applyTo(itemStack, withName)
        return itemStack
    }

    override fun applyTo(itemStack: ItemStack, withName: Boolean) {
        check()

        val itemMeta = itemStack.itemMeta

        if (withName) {
            if (name != null && name!!().get() != null) {
                itemMeta.displayName = name!!().get().string.replaceToColorSymbol()
            } else {
                itemMeta.displayName = ""
            }
        }

        itemMeta.lore = arrayListOf()
        itemMeta.lore.addAll(lores.list)
        itemMeta.itemFlags.clear()
        itemMeta.itemFlags.addAll(flags.list)

        itemMeta.isUnbreakable = unbreakable

        itemStack.itemMeta = itemMeta

        NBT.modify(itemStack) { operableNbt ->
            operableNbt.setLong("cube-bedwars-item", YitIdHelper.nextId())

            val canPlaceOnList = operableNbt.getStringList("CanPlaceOn")
            canPlaceOnList.clear()

            canPlaceOn.list.forEach { canPlaceOnMaterial ->
                canPlaceOnList.add("minecraft:${canPlaceOnMaterial.name.lowercase()}")
            }

            val canDestroyList = operableNbt.getStringList("CanDestroy")
            canDestroyList.clear()

            canDestroy.list.forEach { canDestroyMaterial ->
                canDestroyList.add("minecraft:${canDestroyMaterial.name.lowercase()}")
            }
        }
    }
}