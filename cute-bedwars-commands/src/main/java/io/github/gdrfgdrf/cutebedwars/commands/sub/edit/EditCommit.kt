package io.github.gdrfgdrf.cutebedwars.commands.sub.edit

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.AbstractSubCommand
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.commands.finder.BetterChangesFinder
import io.github.gdrfgdrf.cutebedwars.commands.finder.BetterEditorFinder
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandSyntaxLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.EditorLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object EditCommit : AbstractSubCommand(
    command = ICommands.valueOf("EDIT_COMMIT")
) {
    override fun syntax(): LanguageString? = CommandSyntaxLanguage.EDIT_COMMIT
    override fun description(): LanguageString? = CommandDescriptionLanguage.EDIT_COMMIT

    override fun run(sender: CommandSender, args: Array<String>, paramSchemeIndex: Int) {
        localizationScope(sender) {
            val editor = BetterEditorFinder.find(sender) ?: return@localizationScope
            val changes = BetterChangesFinder.find(sender) ?: return@localizationScope

            val submitter = if (sender is Player) {
                "${sender.name} (${sender.uniqueId})"
            } else {
                "not_a_player"
            }
            val message = if (paramSchemeIndex == 0) {
                args[0]
            } else {
                ""
            }

            message(EditorLanguage.COMMITTING_CHANGES)
                .send()

            val commit = changes.finish()
            commit.finish(submitter, message)
            editor.newChanges()

            val result = editor.tryAdd(commit)
            if (!result) {
                message(EditorLanguage.COMMIT_ERROR)
                    .send()
                return@localizationScope
            }

            message(EditorLanguage.COMMIT_FINISHED)
                .send()
        }
    }
}