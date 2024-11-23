package io.github.gdrfgdrf.cutebedwars.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IItems
import io.github.gdrfgdrf.cutebedwars.abstracts.items.ICommonItem
import io.github.gdrfgdrf.cutebedwars.abstracts.items.IItem
import io.github.gdrfgdrf.cutebedwars.abstracts.items.IItemBuilder
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.localizationScope
import io.github.gdrfgdrf.cutebedwars.abstracts.selection.ISelect
import io.github.gdrfgdrf.cutebedwars.abstracts.selection.ISelections
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.asyncTask
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.replaceToColorSymbol
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommonLanguage
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

          val updateLores: (ISelect, ICommonItem) -> Unit = { select, commonItem ->
              val properties = commonItem.properties
              val pos1 = select.pos1()
              val pos2 = select.pos2()
              var update = false

              if (pos1 != null) {
                  properties.lores[0] = ItemLanguage.SELECTION_TOOL_LORE_ONE.get().string
                      .format(pos1)
                      .replaceToColorSymbol()
                  update = true
              }
              if (pos2 != null) {
                  properties.lores[1] = ItemLanguage.SELECTION_TOOL_LORE_TWO.get().string
                      .format(pos2)
                      .replaceToColorSymbol()
                  update = true
              }

              if (update) {
                  commonItem.update()
              }
          }

          onGiven = onGiven@ { player, commonItem ->
              val selections = ISelections.instance()
              val select = selections.get(player)
              select?.let {
                  updateLores(select, commonItem)
              }
          }
          onLeftClick = onLeftClick@ { event, commonItem ->
              // pos1
              if (!event.hasBlock()) {
                  return@onLeftClick
              }

              val player = event.player
              val clickedBlock = event.clickedBlock

              val coordinate = Coordinate()
              coordinate.x = clickedBlock.x.toDouble()
              coordinate.y = clickedBlock.y.toDouble()
              coordinate.z = clickedBlock.z.toDouble()

              val selections = ISelections.instance()
              val select = selections.get(player)

              select?.let { _ ->
                  select.pos1(coordinate)
                  event.isCancelled = true
                  asyncTask {
                      select.trySpawnParticle(Particle.END_ROD, 1000)
                  }

                  updateLores(select, commonItem)

                  localizationScope(player) {
                      message(CommonLanguage.SELECTED_POS_1)
                          .format0(coordinate)
                          .send()
                  }
              }
          }
          onRightClick = onRightClick@ { event, commonItem ->
              // pos2
              if (!event.hasBlock()) {
                  return@onRightClick
              }

              val player = event.player
              val clickedBlock = event.clickedBlock

              val coordinate = Coordinate()
              coordinate.x = clickedBlock.x.toDouble()
              coordinate.y = clickedBlock.y.toDouble()
              coordinate.z = clickedBlock.z.toDouble()

              val selections = ISelections.instance()
              val select = selections.get(player)
              select?.let { _ ->
                  select.pos2(coordinate)
                  event.isCancelled = true
                  asyncTask {
                      select.trySpawnParticle(Particle.END_ROD, 1000)
                  }

                  updateLores(select, commonItem)

                  localizationScope(player) {
                      message(CommonLanguage.SELECTED_POS_2)
                          .format0(coordinate)
                          .send()
                  }
              }
          }
      }.build(true)
    ),

    ;

    override fun item(): IItem = item
}