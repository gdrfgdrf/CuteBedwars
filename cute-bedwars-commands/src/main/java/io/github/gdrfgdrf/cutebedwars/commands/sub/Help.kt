package io.github.gdrfgdrf.cutebedwars.commands.sub

import io.github.gdrfgdrf.cutebedwars.commands.base.SubCommand
import io.github.gdrfgdrf.cutebedwars.commons.enums.Commands
import io.github.gdrfgdrf.cutebedwars.locale.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.locale.collect.CommandSyntaxLanguage
import org.bukkit.command.CommandSender

object Help : SubCommand(
    command = Commands.HELP,
    syntax = CommandSyntaxLanguage.HELP,
    description = CommandDescriptionLanguage.HELP
){
    override fun run(sender: CommandSender, args: Array<String>) {
        sender.sendMessage("你好世界")
    }
}