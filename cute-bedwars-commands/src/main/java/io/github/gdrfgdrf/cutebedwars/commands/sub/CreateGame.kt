package io.github.gdrfgdrf.cutebedwars.commands.sub

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.commands.base.SubCommand
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandSyntaxLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.command.CommandSender

object CreateGame : SubCommand(
    command = ICommands.valueOf("CREATE_GAME")
) {
    override fun syntax(): LanguageString? = CommandSyntaxLanguage.CREATE_GAME
    override fun description(): LanguageString? = CommandDescriptionLanguage.CREATE_GAME

    override fun run(sender: CommandSender, args: Array<String>, pageSchemeIndex: Int) {
        localizationScope(sender) {



        }
    }
}