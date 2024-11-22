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

    ;

    override fun item(): IItem = item
}