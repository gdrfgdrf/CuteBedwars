package io.github.gdrfgdrf.cutebedwars.abstracts.items

import io.github.gdrfgdrf.cutebedwars.abstracts.utils.ICustomList
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

@Service("item_properties", singleton = false)
interface IItemProperties {
    var material: Material?
    var name: (() -> LanguageString)?

    val lores: ICustomList<String>
    val flags: ICustomList<ItemFlag>

    var onGiven: ((Player, ICommonItem) -> Unit)?
    var onClick: ((PlayerInteractEvent, ICommonItem) -> Unit)?
    var onLeftClick: ((PlayerInteractEvent, ICommonItem) -> Unit)?
    var onRightClick: ((PlayerInteractEvent, ICommonItem) -> Unit)?

    var unbreakable: Boolean
    var droppable: Boolean
    var movable: Boolean
    val canPlaceOn: ICustomList<Material>
    val canDestroy: ICustomList<Material>

    fun check()
    fun copy(): IItemProperties
    fun generate(withName: Boolean = true): ItemStack
    fun applyTo(itemStack: ItemStack, withName: Boolean = true)

    companion object {
        fun create(func: IItemProperties.() -> Unit): IItemProperties {
            val properties = Mediator.get<IItemProperties>(IItemProperties::class.java)
            func(properties as IItemProperties)
            return properties
        }
    }
}