package io.github.gdrfgdrf.cutebedwars.commands.sub

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.IManagers
import io.github.gdrfgdrf.cutebedwars.commands.base.SubCommand
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandSyntaxLanguage
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.command.CommandSender

object ModifyArea : SubCommand(
    command = ICommands.valueOf("MODIFY_AREA")
){
    override fun syntax(): LanguageString? = CommandSyntaxLanguage.MODIFY_AREA
    override fun description(): LanguageString? = CommandDescriptionLanguage.MODIFY_AREA

    override fun run(sender: CommandSender, args: Array<String>) {


    }
}