package io.github.gdrfgdrf.cutebedwars.commands.sub.edit

import io.github.gdrfgdrf.cutebedwars.abstracts.chatpage.IChatPage
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.AbstractSubCommand
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.IParamCombination
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPageRequestTypes
import io.github.gdrfgdrf.cutebedwars.abstracts.information.IProtobufCommitInformation
import io.github.gdrfgdrf.cutebedwars.commands.finder.BetterAreaFinder
import io.github.gdrfgdrf.cutebedwars.languages.collect.AreaManagementLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandSyntaxLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.command.CommandSender

object EditListAreaCommits : AbstractSubCommand(
    command = ICommands.valueOf("EDIT_LIST_AREA_COMMITS")
) {
    override fun syntax(): LanguageString? = CommandSyntaxLanguage.EDIT_LIST_AREA_COMMITS
    override fun description(): LanguageString? = CommandDescriptionLanguage.EDIT_LIST_AREA_COMMITS

    override fun run(sender: CommandSender, args: Array<String>, paramCombination: IParamCombination) {
        localizationScope(sender) {
            val findType = paramCombination.findType()
            val areaIdentifier = paramCombination.string("AREA")
            val pageIndex = paramCombination.pageIndex()

            val areaManager = BetterAreaFinder.find(sender, findType!!, areaIdentifier!!) ?: return@localizationScope
            val message = areaManager.commitStorage().get()
            if (message == null) {
                message(AreaManagementLanguage.AREA_COMMITS_IS_NULL)
                    .send()
                return@localizationScope
            }
            val commitsList = message.commitsList
            if (commitsList.isNullOrEmpty()) {
                message(AreaManagementLanguage.AREA_COMMITS_IS_EMPTY)
                    .send()
                return@localizationScope
            }

            val chatPage = IChatPage.cache(
                sender,
                IPageRequestTypes.valueOf("EDIT_LIST_AREA_COMMITS"),
                areaIdentifier
            ) {
                return@cache arrayListOf()
            }
            commitsList.forEach { commit ->
                chatPage.addPage {
                    IProtobufCommitInformation.instance().convert(sender, commit)
                }
            }
            chatPage.send(pageIndex - 1)
        }
    }

}