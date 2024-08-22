package io.github.gdrfgdrf.cutebedwars.commands.base

import io.github.gdrfgdrf.cutebedwars.commons.enums.Commands
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender

abstract class SubCommand(
    val command: Commands,
) {
    abstract fun syntax(): LanguageString?
    abstract fun description(): LanguageString?

    abstract fun run(sender: CommandSender, args: Array<String>)
    open fun tab(player: CommandSender, args: Array<String>): MutableList<String> {
        return arrayListOf()
    }

    fun hasPermission(sender: CommandSender): Boolean {
        return command.permissions.hasPermission(sender)
    }
}