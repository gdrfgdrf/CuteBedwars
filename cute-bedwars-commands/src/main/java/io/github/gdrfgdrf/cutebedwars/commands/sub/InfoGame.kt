package io.github.gdrfgdrf.cutebedwars.commands.sub

import io.github.gdrfgdrf.cutebedwars.abstracts.chatpage.IChatPage
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPageRequestTypes
import io.github.gdrfgdrf.cutebedwars.abstracts.game.information.IGameInformation
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game.IGameContext
import io.github.gdrfgdrf.cutebedwars.commands.finder.BetterAreaFinder
import io.github.gdrfgdrf.cutebedwars.commands.finder.BetterGameFinder
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.AbstractSubCommand
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandSyntaxLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.cutebedwars.utils.extension.toIntOrDefault
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.command.CommandSender

object InfoGame : AbstractSubCommand(
    command = ICommands.valueOf("INFO_GAME")
) {
    override fun syntax(): LanguageString? = CommandSyntaxLanguage.INFO_GAME
    override fun description(): LanguageString? = CommandDescriptionLanguage.INFO_GAME

    override fun run(sender: CommandSender, args: Array<String>, paramSchemeIndex: Int) {
        localizationScope(sender) {
            val findType = args[0]
            val areaIdentifier = args[1]
            val pageIndex = if (paramSchemeIndex == 1 || paramSchemeIndex == 3) {
                if (paramSchemeIndex == 1) {
                    args[2].toIntOrDefault(1)
                } else {
                    args[4].toIntOrDefault(1)
                }
            } else {
                1
            }
            var gameIdentifier = ""

            val areaManager = BetterAreaFinder.find(sender, findType, areaIdentifier) ?: return@localizationScope
            val gameContexts = arrayListOf<IGameContext>()

            if (paramSchemeIndex == 0 || paramSchemeIndex == 1) {
                gameContexts.addAll(areaManager.context().games())
            }
            if (paramSchemeIndex == 2 || paramSchemeIndex == 3) {
                val gameFindType = args[2]
                gameIdentifier = args[3]

                val gameContext =
                    BetterGameFinder.find(sender, gameFindType, areaManager, gameIdentifier) ?: return@localizationScope
                gameContexts.add(gameContext)
            }

            val chatPage = IChatPage.get(
                sender,
                IPageRequestTypes.valueOf("INFO_GAME"),
                areaIdentifier + "_" + gameIdentifier
            ) {
                return@get arrayListOf()
            }
            gameContexts.forEach { context ->
                chatPage.addPage {
                    IGameInformation.get().convert(sender, context)
                }
            }
            chatPage.send(pageIndex - 1)
        }

    }
}