package io.github.gdrfgdrf.cutebedwars.commands.sub

import io.github.gdrfgdrf.cutebedwars.abstracts.chatpage.IChatPage
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.AbstractSubCommand
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.IParamCombination
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPageRequestTypes
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game.IGameContext
import io.github.gdrfgdrf.cutebedwars.abstracts.information.IGameInformation
import io.github.gdrfgdrf.cutebedwars.commands.finder.BetterAreaFinder
import io.github.gdrfgdrf.cutebedwars.commands.finder.BetterGameFinder
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandSyntaxLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.command.CommandSender

object InfoGame : AbstractSubCommand(
    command = ICommands.valueOf("INFO_GAME")
) {
    override fun syntax(): LanguageString? = CommandSyntaxLanguage.INFO_GAME
    override fun description(): LanguageString? = CommandDescriptionLanguage.INFO_GAME

    override fun run(sender: CommandSender, args: Array<String>, paramCombination: IParamCombination) {
        localizationScope(sender) {
            val findType = paramCombination.findType()
            val areaIdentifier = paramCombination.areaIdentifier()
            val pageIndex = paramCombination.pageIndex()
            val gameIdentifier = paramCombination.gameIdentifier()

            val areaManager = BetterAreaFinder.find(sender, findType!!, areaIdentifier) ?: return@localizationScope
            val gameContexts = arrayListOf<IGameContext>()

            if (paramCombination.paramSchemeIndex == 0 || paramCombination.paramSchemeIndex == 1) {
                gameContexts.addAll(areaManager.context().games())
            }
            if (paramCombination.paramSchemeIndex == 2 || paramCombination.paramSchemeIndex == 3) {
                val gameFindType = paramCombination.findType(1)

                val foundGameContexts =
                    BetterGameFinder.multipleResult(sender, gameFindType!!, areaManager, gameIdentifier)
                        ?: return@localizationScope
                gameContexts.addAll(foundGameContexts)
            }

            val chatPage = IChatPage.cache(
                sender,
                IPageRequestTypes.valueOf("INFO_GAME"),
                areaIdentifier + "_" + gameIdentifier
            ) {
                return@cache arrayListOf()
            }
            gameContexts.forEach { context ->
                chatPage.addPage {
                    IGameInformation.instance().convert(sender, context)
                }
            }
            chatPage.send(pageIndex - 1)
        }

    }
}