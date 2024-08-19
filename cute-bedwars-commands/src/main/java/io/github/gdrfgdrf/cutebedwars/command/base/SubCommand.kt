package io.github.gdrfgdrf.cutebedwars.command.base

import io.github.gdrfgdrf.cutebedwars.command.manager.SubCommandManager
import io.github.gdrfgdrf.cutebedwars.commons.enums.Commands
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

abstract class SubCommand(
    val command: Commands,
    val syntax: LanguageString?,
    val description: LanguageString?
) {
    abstract fun run(sender: CommandSender, args: Array<String>)
    fun tab(player: CommandSender, args: Array<String>): MutableList<String> {
        return arrayListOf()
    }

    fun hasPermission(sender: CommandSender): Boolean {
        return sender.hasPermission(command.permissions.get())
    }
}