package io.github.gdrfgdrf.cutebedwars.commands.sub

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.commands.base.SubCommand
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandSyntaxLanguage
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.command.CommandSender

object InfoArea : SubCommand(
    command = ICommands.valueOf("INFO_AREA")
){
    override fun syntax(): LanguageString? = CommandSyntaxLanguage.INFO_AREA
    override fun description(): LanguageString? = CommandDescriptionLanguage.INFO_AREA

    override fun run(sender: CommandSender, args: Array<String>) {

    }
}