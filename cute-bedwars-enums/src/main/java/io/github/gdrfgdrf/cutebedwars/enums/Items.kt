package io.github.gdrfgdrf.cutebedwars.enums

import de.tr7zw.changeme.nbtapi.NBT
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IItems
import io.github.gdrfgdrf.cutebedwars.abstracts.items.given.ISpecialGivenItem
import io.github.gdrfgdrf.cutebedwars.abstracts.items.item.IBuiltItem
import io.github.gdrfgdrf.cutebedwars.abstracts.items.builder.IItemBuilder
import io.github.gdrfgdrf.cutebedwars.abstracts.items.item.IItem
import io.github.gdrfgdrf.cutebedwars.abstracts.items.item.ISpecialBuiltItem
import io.github.gdrfgdrf.cutebedwars.abstracts.items.property.IItemProperties
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.localizationScope
import io.github.gdrfgdrf.cutebedwars.abstracts.selection.ISelect
import io.github.gdrfgdrf.cutebedwars.abstracts.selection.ISelections
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.asyncTask
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.replaceToColorSymbol
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommonLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.ItemLanguage
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumServiceImpl
import org.bukkit.Material
import org.bukkit.Particle

@EnumServiceImpl("items")
enum class Items(override val item: IItem) : IItems {
    SELECTION_TOOL(
      IItemBuilder.new().modify {
          material = Material.STICK
          name = {
              ItemLanguage.SELECTION_TOOL_NAME
          }
          unbreakable = true
          movable = true
          droppable = false

          onLeftClick = onLeftClick@ { event, _ ->
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

                  localizationScope(player) {
                      message(CommonLanguage.SELECTED_POS_1)
                          .format0(coordinate)
                          .send()
                  }
              }
          }
          onRightClick = onRightClick@ { event, _ ->
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

                  localizationScope(player) {
                      message(CommonLanguage.SELECTED_POS_2)
                          .format0(coordinate)
                          .send()
                  }
              }
          }

          setPostProcessor(this, "SELECTION_TOOL", true)
      }.buildSpecial()
    ),

    ;

    override fun builtItem(): IBuiltItem {
        return item as IBuiltItem
    }

    override fun special(): ISpecialBuiltItem {
        return item as ISpecialBuiltItem
    }
}

private fun setPostProcessor(itemProperties: IItemProperties, enum: String, special: Boolean) {
    itemProperties.postProcessor = { itemStack ->
        NBT.modify(itemStack) { nbt ->
            nbt.setString("cute-bedwars-item-enum", enum)
            nbt.setBoolean("cute-bedwars-item-special", special)
        }
    }
}