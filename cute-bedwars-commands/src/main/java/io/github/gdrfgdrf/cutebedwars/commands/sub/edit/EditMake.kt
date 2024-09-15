package io.github.gdrfgdrf.cutebedwars.commands.sub.edit

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.AbstractSubCommand
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandSyntaxLanguage
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.command.CommandSender

object EditMake : AbstractSubCommand(
    command = ICommands.valueOf("EDIT_MAKE")
) {
    override fun syntax(): LanguageString? = CommandSyntaxLanguage.EDIT_MAKE
    override fun description(): LanguageString? = CommandDescriptionLanguage.EDIT_MAKE

    override fun run(sender: CommandSender, args: Array<String>, paramSchemeIndex: Int) {
        val changeTypeName = args[0]
        sender.sendMessage(changeTypeName)


    }
}