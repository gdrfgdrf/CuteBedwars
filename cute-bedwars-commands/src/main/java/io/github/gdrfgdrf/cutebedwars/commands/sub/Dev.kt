package io.github.gdrfgdrf.cutebedwars.commands.sub

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.AbstractSubCommand
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.IParamCombination
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IItems
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object Dev : AbstractSubCommand(
    command = ICommands.valueOf("DEV")
) {
    override fun syntax(): LanguageString? = null
    override fun description(): LanguageString? = null

    override fun run(sender: CommandSender, args: Array<String>, paramCombination: IParamCombination) {
        val item = IItems.valueOf("DEV_TOOL").item()
        val item2 = IItems.valueOf("DEV_TOOL_2").item()

        val givenItem1 = item.give(sender as Player, slotIndex = 3)
//        asyncTask {
//            for (i in 0 until 5) {
//                sleepSafely(1000)
//
//                val properties = givenItem1.properties
//                properties.name = {
//                    LanguageString("${i + 1}")
//                }
//                properties.lores.list.clear()
//                properties.lores.add("${i + 1}")
//
//                givenItem1.update()
//            }
//
//            givenItem1.remove()
//        }

        val givenItem2 = item2.give(sender, slotIndex = 5)
//        asyncTask {
//            for (i in 0 until 10) {
//                sleepSafely(1000)
//
//                val properties = givenItem2.properties
//                properties.name = {
//                    LanguageString("${i + 1} (2)")
//                }
//                properties.lores.list.clear()
//                properties.lores.add("${i + 1} (2)")
//
//                givenItem2.update()
//            }
//
//            givenItem2.remove()
//        }
    }
}