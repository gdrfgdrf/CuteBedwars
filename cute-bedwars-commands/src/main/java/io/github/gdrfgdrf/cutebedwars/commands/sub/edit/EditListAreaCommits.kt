package io.github.gdrfgdrf.cutebedwars.commands.sub.edit

import io.github.gdrfgdrf.cutebedwars.abstracts.chatpage.IChatPage
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.AbstractSubCommand
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPageRequestTypes
import io.github.gdrfgdrf.cutebedwars.abstracts.information.IProtobufCommitInformation
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.toIntOrDefault
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

    override fun run(sender: CommandSender, args: Array<String>, paramSchemeIndex: Int) {
        localizationScope(sender) {
            val findType = args[0]
            val areaIdentifier = args[1]
            val pageIndex = if (paramSchemeIndex == 0) {
                1
            } else {
                if (paramSchemeIndex == 1) {
                    args[2].toIntOrDefault(1)
                } else {
                    1
                }
            }

            val areaManager = BetterAreaFinder.find(sender, findType, areaIdentifier) ?: return@localizationScope
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