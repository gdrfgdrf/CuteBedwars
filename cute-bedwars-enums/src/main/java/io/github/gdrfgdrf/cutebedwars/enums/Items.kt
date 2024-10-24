package io.github.gdrfgdrf.cutebedwars.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IItems
import io.github.gdrfgdrf.cutebedwars.abstracts.items.IItem
import io.github.gdrfgdrf.cutebedwars.abstracts.items.IItemBuilder
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumServiceImpl
import org.bukkit.Material
import org.bukkit.inventory.ItemFlag

@EnumServiceImpl("items")
enum class Items(private val item: IItem) : IItems {
    DEV_TOOL(
        IItemBuilder.new().material(Material.DIAMOND_PICKAXE)
            .name {
                LanguageString("dev_tool")
            }
            .lore("test lore 1")
            .lore("test lore 2")
            .lore("test lore 3")
            .unbreakable(true)
            .canDestroy(Material.STONE)
            .onClick {
                val player = it.player
                player.sendMessage("onClick")
            }.onLeftClick {
                val player = it.player
                player.sendMessage("onLeftClick")
            }.onRightClick {
                val player = it.player
                player.sendMessage("onRightClick")
            }.build()
    ),
    DEV_TOOL_2(
        IItemBuilder.new().material(Material.DIAMOND_BLOCK)
            .name {
                LanguageString("dev_tool_2")
            }
            .lore("test lore 1 (2)")
            .lore("test lore 2 (2)")
            .lore("test lore 3 (2)")
            .canPlaceOn(Material.STONE)
            .droppable(true)
            .onClick {
                val player = it.player
                player.sendMessage("onClick (2)")
            }.onLeftClick {
                val player = it.player
                player.sendMessage("onLeftClick (2)")
            }.onRightClick {
                val player = it.player
                player.sendMessage("onRightClick (2)")
            }.build()
    )


    ;

    override fun item(): IItem = item
}