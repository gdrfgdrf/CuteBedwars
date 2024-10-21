package io.github.gdrfgdrf.cutebedwars.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IItems
import io.github.gdrfgdrf.cutebedwars.abstracts.items.IItem
import io.github.gdrfgdrf.cutebedwars.abstracts.items.IItemBuilder
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumServiceImpl
import org.bukkit.Material

@EnumServiceImpl("items")
enum class Items(private val item: IItem) : IItems {
    DEV_TOOL(
        IItemBuilder.new().material(Material.STICK)
            .addLore("test lore 1")
            .addLore("test lore 2")
            .addLore("test lore 3")
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
    )


    ;

    override fun item(): IItem = item
}