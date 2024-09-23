package io.github.gdrfgdrf.cutebedwars.commands.sub.edit

import io.github.gdrfgdrf.cutebedwars.abstracts.chatpage.IChatPage
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.AbstractSubCommand
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPageRequestTypes
import io.github.gdrfgdrf.cutebedwars.abstracts.information.IChangesInformation
import io.github.gdrfgdrf.cutebedwars.commands.common.ParamScheme
import io.github.gdrfgdrf.cutebedwars.commands.finder.BetterChangesFinder
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandSyntaxLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.EditorLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.cutebedwars.utils.extension.toIntOrDefault
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.command.CommandSender

object EditListChanges : AbstractSubCommand(
    command = ICommands.valueOf("EDIT_LIST_CHANGES")
) {
    override fun syntax(): LanguageString? = CommandSyntaxLanguage.EDIT_LIST_CHANGES
    override fun description(): LanguageString? = CommandDescriptionLanguage.EDIT_LIST_CHANGES

    override fun run(sender: CommandSender, args: Array<String>, paramSchemeIndex: Int) {
        localizationScope(sender) {
            val changes = BetterChangesFinder.find(sender) ?: return@localizationScope
            val pageIndex = if (paramSchemeIndex == ParamScheme.NO_MATCH) {
                1
            } else {
                args[0].toIntOrDefault(1)
            }

            if (changes.size() <= 0) {
                message(EditorLanguage.CHANGE_LIST_IS_EMPTY)
                    .send()
                return@localizationScope
            }

            val messages = IChangesInformation.get().convert(sender, changes)

            val chatPage = IChatPage.get(sender, IPageRequestTypes.valueOf("EDIT_LIST_CHANGES"), messages.size.toString()) {
                messages
            }
            chatPage.send(pageIndex - 1)
        }
    }
}