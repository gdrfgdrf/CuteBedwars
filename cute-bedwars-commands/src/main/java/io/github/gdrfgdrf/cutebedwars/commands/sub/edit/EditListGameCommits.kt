package io.github.gdrfgdrf.cutebedwars.commands.sub.edit

import io.github.gdrfgdrf.cutebedwars.abstracts.chatpage.IChatPages
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.AbstractSubCommand
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.IParamCombination
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPageRequestTypes
import io.github.gdrfgdrf.cutebedwars.abstracts.information.IProtobufCommitInformation
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILanguageString
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ITranslationAgent
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.localizationScope
import io.github.gdrfgdrf.cutebedwars.commands.finder.BetterAreaFinder
import io.github.gdrfgdrf.cutebedwars.commands.finder.BetterGameFinder
import io.github.gdrfgdrf.cutebedwars.languages.collect.AreaManagementLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandSyntaxLanguage
import org.bukkit.command.CommandSender

object EditListGameCommits : AbstractSubCommand(
    command = ICommands.valueOf("EDIT_LIST_GAME_COMMITS")
) {
    override val syntax: ILanguageString = CommandSyntaxLanguage.EDIT_LIST_GAME_COMMITS
    override val description: ILanguageString = CommandDescriptionLanguage.EDIT_LIST_GAME_COMMITS

    override fun run(sender: CommandSender, args: Array<String>, paramCombination: IParamCombination) {
        localizationScope(sender) {
            val areaFindType = paramCombination.findType()
            val areaIdentifier = paramCombination.areaIdentifier()
            val gameFindType = paramCombination.findType(1)
            val gameIdentifier = paramCombination.gameIdentifier(1)
            val pageIndex = paramCombination.pageIndex()

            val areaManager = BetterAreaFinder.find(sender, areaFindType!!, areaIdentifier) ?: return@localizationScope
            val gameContext = BetterGameFinder.find(sender, gameFindType!!, areaManager, gameIdentifier) ?: return@localizationScope

            val protobufCommits = gameContext.commitStorage.commits()
            if (protobufCommits == null) {
                message(AreaManagementLanguage.GAME_COMMITS_IS_NULL)
                    .send()
                return@localizationScope
            }
            val commitsList = protobufCommits.commitsList
            if (commitsList.isNullOrEmpty()) {
                message(AreaManagementLanguage.GAME_COMMITS_IS_EMPTY)
                    .send()
                return@localizationScope
            }

            val chatPage = IChatPages.instance().cache(
                sender,
                IPageRequestTypes.valueOf("EDIT_LIST_GAME_COMMITS"),
                areaIdentifier
            ) {
                return@cache arrayListOf()
            }
            chatPage.enableDefaultTopAndBottom = false

            commitsList.forEach { commit ->
                chatPage.addPage { pageIndex ->
                    arrayListOf<ITranslationAgent>().apply {
                        add(
                            message(AreaManagementLanguage.GAME_COMMITS_TOP)
                                .format0(commit.submitter, pageIndex, commitsList.size)
                        )
                        addAll(
                            IProtobufCommitInformation.instance().convert(sender, commit)
                        )
                        add(
                            message(AreaManagementLanguage.GAME_COMMITS_BOTTOM)
                                .format0(commit.submitter, pageIndex, commitsList.size)
                        )
                    }
                }
            }
            chatPage.send(pageIndex - 1)
        }
    }
}