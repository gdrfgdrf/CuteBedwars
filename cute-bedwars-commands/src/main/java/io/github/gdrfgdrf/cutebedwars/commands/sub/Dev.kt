package io.github.gdrfgdrf.cutebedwars.commands.sub

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.AbstractSubCommand
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.IParamCombination
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IItems
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILanguageString
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.two.IPoint2D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.two.ICircle2D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.common.mathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.selection.ISelections
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object Dev : AbstractSubCommand(
    command = ICommands.valueOf("DEV")
) {
    override val syntax: ILanguageString? = null
    override val description: ILanguageString? = null

    override fun run(sender: CommandSender, args: Array<String>, paramCombination: IParamCombination) {
        if (args[0] == "0") {
            ISelections.instance().create(sender as Player)
            val selectionTool = IItems.valueOf("SELECTION_TOOL")
            selectionTool.item.give(sender)
            return
        }
        if (args[0] == "1") {
            val centerX = args[1].toDoubleOrNull() ?: return
            val centerY = args[2].toDoubleOrNull() ?: return
            val R = args[3].toDoubleOrNull() ?: return
            val step = args[4].toDoubleOrNull() ?: return

            val center = IPoint2D.new(centerX, centerY)
            val circle2d = ICircle2D.new(center, R.mathNumber())

            runCatching {
                val divideResult = circle2d.divide(step.mathNumber())
                if (divideResult.isEmpty()) {
                    sender.sendMessage("empty")
                }
                divideResult.forEach {
                    sender.sendMessage(it.toString())
                }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }
}