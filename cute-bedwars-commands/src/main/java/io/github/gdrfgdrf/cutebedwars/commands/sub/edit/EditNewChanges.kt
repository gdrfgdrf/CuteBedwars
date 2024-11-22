package io.github.gdrfgdrf.cutebedwars.commands.sub.edit

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.AbstractSubCommand
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.IParamCombination
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IRequestTypes
import io.github.gdrfgdrf.cutebedwars.abstracts.requests.IRequests
import io.github.gdrfgdrf.cutebedwars.commands.finder.BetterEditorFinder
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandSyntaxLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.EditorLanguage
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.localizationScope
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.command.CommandSender
import java.util.concurrent.TimeUnit

object EditNewChanges : AbstractSubCommand(
    command = ICommands.valueOf("EDIT_NEW_CHANGES")
) {
    override fun syntax(): LanguageString? = CommandSyntaxLanguage.EDIT_NEW_CHANGES
    override fun description(): LanguageString? = CommandDescriptionLanguage.EDIT_NEW_CHANGES

    override fun run(sender: CommandSender, args: Array<String>, paramCombination: IParamCombination) {
        localizationScope(sender) {
            val editor = BetterEditorFinder.find(sender) ?: return@localizationScope
            val requests = IRequests.instance()

            if (editor.currentChanges() != null) {
                val pair = requests.auto(type = IRequestTypes.valueOf("EDIT_NEW_CHANGES"), sender = sender)
                val new = pair.first
                val request = pair.second

                if (new) {
                    message(EditorLanguage.REPLACING_CHANGE_LIST_WARNING)
                        .format0(TimeUnit.SECONDS.convert(request.timeout(), request.timeUnit()))
                        .send()
                    return@localizationScope
                }
            }
            requests.removeForAuto(type = IRequestTypes.valueOf("EDIT_NEW_CHANGES"), sender = sender)

            message(EditorLanguage.CREATING_CHANGE_LIST)
                .send()

            editor.newChanges()

            message(EditorLanguage.CREATE_CHANGE_LIST_FINISHED)
                .send()
        }
    }
}