package io.github.gdrfgdrf.cutebedwars.abstracts.items

import io.github.gdrfgdrf.cutebedwars.abstracts.utils.ICustomList
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import org.bukkit.Material
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

@Service("item_properties", singleton = false)
interface IItemProperties {
    var material: Material?
    var name: (() -> LanguageString)?

    val lores: ICustomList<String>
    val flags: ICustomList<ItemFlag>

    var onClick: ((PlayerInteractEvent) -> Unit)?
    var onLeftClick: ((PlayerInteractEvent) -> Unit)?
    var onRightClick: ((PlayerInteractEvent) -> Unit)?

    var unbreakable: Boolean
    var droppable: Boolean
    var movable: Boolean
    val canPlaceOn: ICustomList<Material>
    val canDestroy: ICustomList<Material>

    fun check()
    fun copy(): IItemProperties
    fun applyTo(itemStack: ItemStack)

    companion object {
        fun create(func: IItemProperties.() -> Unit): IItemProperties {
            val properties = Mediator.get<IItemProperties>(IItemProperties::class.java)
            func(properties as IItemProperties)
            return properties
        }
    }
}