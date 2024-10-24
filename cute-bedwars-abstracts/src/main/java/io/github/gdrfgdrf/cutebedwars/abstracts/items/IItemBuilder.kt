package io.github.gdrfgdrf.cutebedwars.abstracts.items

import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import org.bukkit.Material
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemFlag

@Service("item_builder", singleton = false)
interface IItemBuilder {
    fun material(): Material?
    fun name(): (() -> LanguageString)?
    fun lores(): MutableList<String>
    fun flags(): MutableList<ItemFlag>
    fun onClick(): ((PlayerInteractEvent) -> Unit)?
    fun onLeftClick(): ((PlayerInteractEvent) -> Unit)?
    fun onRightClick(): ((PlayerInteractEvent) -> Unit)?

    fun unbreakable(): Boolean
    fun canPlaceOn(): MutableList<Material>
    fun canDestroy(): MutableList<Material>
    fun droppable(): Boolean

    fun build(): IItem
    fun material(material: Material): IItemBuilder
    fun name(string: () -> LanguageString): IItemBuilder
    fun lore(string: String): IItemBuilder
    fun removeLore(string: String): IItemBuilder
    fun removeLore(index: Int): IItemBuilder
    fun flag(vararg flag: ItemFlag): IItemBuilder
    fun removeFlag(vararg flag: ItemFlag): IItemBuilder
    fun onClick(onClick: (PlayerInteractEvent) -> Unit): IItemBuilder
    fun onLeftClick(onLeftClick: (PlayerInteractEvent) -> Unit): IItemBuilder
    fun onRightClick(onRightClick: (PlayerInteractEvent) -> Unit): IItemBuilder

    fun unbreakable(unbreakable: Boolean): IItemBuilder
    fun canPlaceOn(vararg material: Material): IItemBuilder
    fun removeCanPlaceOn(vararg material: Material): IItemBuilder
    fun canDestroy(vararg material: Material): IItemBuilder
    fun removeCanDestroy(vararg material: Material): IItemBuilder
    fun droppable(droppable: Boolean): IItemBuilder

    companion object {
        fun new(): IItemBuilder = Mediator.get(IItemBuilder::class.java)!!
    }
}