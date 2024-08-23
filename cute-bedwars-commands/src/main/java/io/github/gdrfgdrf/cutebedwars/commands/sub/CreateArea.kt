package io.github.gdrfgdrf.cutebedwars.commands.sub

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.commands.base.SubCommand
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandSyntaxLanguage
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.command.CommandSender

object CreateArea : SubCommand(
    command = ICommands.get("CREATE_AREA")
){
    override fun syntax(): LanguageString? = CommandSyntaxLanguage.CREATE_AREA

    override fun description(): LanguageString? = CommandDescriptionLanguage.CREATE_AREA

    override fun run(sender: CommandSender, args: Array<String>) {

    }
}