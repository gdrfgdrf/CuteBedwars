package io.github.gdrfgdrf.cutebedwars.commands.sub.edit

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.AbstractSubCommand
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IRequestTypes
import io.github.gdrfgdrf.cutebedwars.abstracts.requests.IRequests
import io.github.gdrfgdrf.cutebedwars.commands.finder.BetterEditorFinder
import io.github.gdrfgdrf.cutebedwars.languages.collect.AreaManagementLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandSyntaxLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.EditorLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.command.CommandSender
import java.util.concurrent.TimeUnit

object EditExit : AbstractSubCommand(
    command = ICommands.valueOf("EDIT_EXIT")
) {
    override fun syntax(): LanguageString? = CommandSyntaxLanguage.EDIT_EXIT
    override fun description(): LanguageString? = CommandDescriptionLanguage.EDIT_EXIT

    override fun run(sender: CommandSender, args: Array<String>, paramSchemeIndex: Int) {
        localizationScope(sender) {
            val editor = BetterEditorFinder.find(sender) ?: return@localizationScope

            val applyChange = if (paramSchemeIndex == 0) {
                args[0].toBoolean()
            } else {
                true
            }
            val requests = IRequests.get()

            if (!applyChange) {
                val pair = requests.auto(type = IRequestTypes.valueOf("EDIT_EXITING_WITHOUT_APPLYING"), sender = sender)
                val new = pair.first
                val request = pair.second

                if (new) {
                    message(EditorLanguage.EXITING_WITHOUT_APPLYING_WARNING)
                        .format(TimeUnit.SECONDS.convert(request.timeout(), request.timeUnit()))
                        .send()
                    return@localizationScope
                }
            }
            requests.removeForAuto(type = IRequestTypes.valueOf("EDIT_EXITING_WITHOUT_APPLYING"), sender = sender)

            message(EditorLanguage.EXITING_EDITOR)
                .send()

            if (applyChange) {
                message(EditorLanguage.APPLYING_CHANGES)
                    .send()
            }
            editor.exit(applyChange)

            message(EditorLanguage.EXIT_FINISHED)
                .send()
        }
    }
}