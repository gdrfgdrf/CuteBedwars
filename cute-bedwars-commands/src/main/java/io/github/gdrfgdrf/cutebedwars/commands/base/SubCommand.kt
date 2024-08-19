package io.github.gdrfgdrf.cutebedwars.commands.base

import io.github.gdrfgdrf.cutebedwars.commons.enums.Commands
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.command.CommandSender

abstract class SubCommand(
    val command: Commands,
    val syntax: LanguageString?,
    val description: LanguageString?
) {
    abstract fun run(sender: CommandSender, args: Array<String>)
    open fun tab(player: CommandSender, args: Array<String>): MutableList<String> {
        return arrayListOf()
    }

    fun hasPermission(sender: CommandSender): Boolean {
        return sender.hasPermission(command.permissions.get())
    }
}