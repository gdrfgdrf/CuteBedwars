package io.github.gdrfgdrf.cutebedwars.abstracts.items

import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import org.bukkit.Material
import org.bukkit.event.player.PlayerInteractEvent

@Service("item_builder", singleton = false)
interface IItemBuilder {
    fun material(): Material?
    fun name(): (() -> LanguageString)?
    fun lores(): MutableList<String>
    fun onClick(): ((PlayerInteractEvent) -> Unit)?
    fun onLeftClick(): ((PlayerInteractEvent) -> Unit)?
    fun onRightClick(): ((PlayerInteractEvent) -> Unit)?

    fun material(material: Material): IItemBuilder
    fun name(string: () -> LanguageString): IItemBuilder
    fun addLore(string: String): IItemBuilder
    fun removeLore(string: String): IItemBuilder
    fun removeLore(index: Int): IItemBuilder
    fun onClick(onClick: (PlayerInteractEvent) -> Unit): IItemBuilder
    fun onLeftClick(onLeftClick: (PlayerInteractEvent) -> Unit): IItemBuilder
    fun onRightClick(onRightClick: (PlayerInteractEvent) -> Unit): IItemBuilder

    fun build(): IItem

    companion object {
        fun new(): IItemBuilder = Mediator.get(IItemBuilder::class.java)!!
    }
}