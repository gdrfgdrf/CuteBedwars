package io.github.gdrfgdrf.cutebedwars.abstracts.commands

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILanguageString
import org.bukkit.command.CommandSender

abstract class AbstractSubCommand(
    val command: ICommands,
) {
    abstract fun syntax(): ILanguageString?
    abstract fun description(): ILanguageString?

    abstract fun run(sender: CommandSender, args: Array<String>, paramCombination: IParamCombination)
    open fun tab(sender: CommandSender, args: Array<String>): MutableList<String> {
        return arrayListOf()
    }

    fun hasPermission(sender: CommandSender): Boolean {
        return command.permissions.hasPermission(sender)
    }
}