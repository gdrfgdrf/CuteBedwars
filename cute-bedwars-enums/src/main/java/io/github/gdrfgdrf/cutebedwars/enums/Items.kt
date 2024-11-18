package io.github.gdrfgdrf.cutebedwars.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IItems
import io.github.gdrfgdrf.cutebedwars.abstracts.items.IItem
import io.github.gdrfgdrf.cutebedwars.abstracts.items.IItemBuilder
import io.github.gdrfgdrf.cutebedwars.abstracts.selection.ISelections
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.asyncTask
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate
import io.github.gdrfgdrf.cutebedwars.languages.collect.ItemLanguage
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumServiceImpl
import org.bukkit.Material
import org.bukkit.Particle

@EnumServiceImpl("items")
enum class Items(private val item: IItem) : IItems {
    SELECTION_TOOL(
      IItemBuilder.new().modify {
          material = Material.STICK
          name = {
              ItemLanguage.SELECTION_TOOL_NAME
          }
          unbreakable = true
          movable = true
          droppable = false
          onLeftClick = onLeftClick@ {
              // pos1
              if (!it.hasBlock()) {
                  return@onLeftClick
              }

              val player = it.player
              val clickedBlock = it.clickedBlock

              val coordinate = Coordinate()
              coordinate.x = clickedBlock.x.toDouble()
              coordinate.y = clickedBlock.y.toDouble()
              coordinate.z = clickedBlock.z.toDouble()

              val selections = ISelections.instance()
              val select = selections.get(player)

              select?.let { _ ->
                  select.pos1(coordinate)
                  it.isCancelled = true
                  asyncTask {
                      select.trySpawnParticle(Particle.REDSTONE, 50)
                  }

              }
          }
          onRightClick = onRightClick@ {
              // pos2
              if (!it.hasBlock()) {
                  return@onRightClick
              }

              val player = it.player
              val clickedBlock = it.clickedBlock

              val coordinate = Coordinate()
              coordinate.x = clickedBlock.x.toDouble()
              coordinate.y = clickedBlock.y.toDouble()
              coordinate.z = clickedBlock.z.toDouble()

              val selections = ISelections.instance()
              val select = selections.get(player)
              select?.let { _ ->
                  select.pos2(coordinate)
                  it.isCancelled = true
                  asyncTask {
                      select.trySpawnParticle(Particle.REDSTONE, 10)
                  }
              }
          }
      }.build(true)
    ),
    DEV_TOOL(
        IItemBuilder.new().modify {
            material = Material.DIAMOND_PICKAXE
            name = {
                LanguageString("dev_tool")
            }
            lores.add("test lore 1", "test lore 2", "test lore 3")
            unbreakable = true
            movable = false
            canDestroy.add(Material.STONE)
            onClick = {
                val player = it.player
                player.sendMessage("onClick")
            }
            onLeftClick = {
                val player = it.player
                player.sendMessage("onLeftClick")
            }
            onRightClick = {
                val player = it.player
                player.sendMessage("onRightClick")
            }
        }.build(true)
    ),
    DEV_TOOL_2(
        IItemBuilder.new().modify {
            material = Material.DIAMOND_BLOCK
            name = {
                LanguageString("dev_tool_2")
            }
            lores.add("test lore 1 (2)", "test lore 2 (2)", "test lore 3 (2)")
            canPlaceOn.add(Material.STONE)
            onClick = {
                val player = it.player
                player.sendMessage("onClick (2)")
            }
            onLeftClick = {
                val player = it.player
                player.sendMessage("onLeftClick (2)")
            }
            onRightClick = {
                val player = it.player
                player.sendMessage("onRightClick (2)")
            }
        }.build(true)
    )


    ;

    override fun item(): IItem = item
}