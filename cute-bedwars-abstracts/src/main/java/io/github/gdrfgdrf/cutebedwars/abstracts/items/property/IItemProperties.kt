package io.github.gdrfgdrf.cutebedwars.abstracts.items.property

import io.github.gdrfgdrf.cutebedwars.abstracts.items.given.ISpecialGivenItem
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILanguageString
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.ICustomList
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
    var name: (() -> ILanguageString)?

    val lores: ICustomList<String>
    val flags: ICustomList<ItemFlag>

    var onGiven: ((Player, ISpecialGivenItem) -> Unit)?
    var onClick: ((PlayerInteractEvent, ISpecialGivenItem) -> Unit)?
    var onLeftClick: ((PlayerInteractEvent, ISpecialGivenItem) -> Unit)?
    var onRightClick: ((PlayerInteractEvent, ISpecialGivenItem) -> Unit)?

    var unbreakable: Boolean
    var droppable: Boolean
    var movable: Boolean
    var stackable: Boolean

    val canPlaceOn: ICustomList<Material>
    val canDestroy: ICustomList<Material>

    var postProcessor: ((ItemStack) -> Unit)?

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