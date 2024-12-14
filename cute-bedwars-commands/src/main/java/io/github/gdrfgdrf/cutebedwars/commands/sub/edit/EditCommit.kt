package io.github.gdrfgdrf.cutebedwars.commands.sub.edit

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.AbstractSubCommand
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.IParamCombination
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.AbstractAreaEditor
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.AbstractGameEditor
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILanguageString
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.asyncTask
import io.github.gdrfgdrf.cutebedwars.commands.finder.BetterChangesFinder
import io.github.gdrfgdrf.cutebedwars.commands.finder.BetterEditorFinder
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandSyntaxLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.EditorLanguage
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.localizationScope
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object EditCommit : AbstractSubCommand(
    command = ICommands.valueOf("EDIT_COMMIT")
) {
    override val syntax: ILanguageString = CommandSyntaxLanguage.EDIT_COMMIT
    override val description: ILanguageString = CommandDescriptionLanguage.EDIT_COMMIT

    override fun run(sender: CommandSender, args: Array<String>, paramCombination: IParamCombination) {
        localizationScope(sender) {
            val editor = BetterEditorFinder.find(sender) ?: return@localizationScope
            if (editor.t == null) {
                return@localizationScope
            }

            val changes = BetterChangesFinder.find(sender) ?: return@localizationScope

            val submitter = if (sender is Player) {
                "${sender.name} (${sender.uniqueId})"
            } else {
                "Console"
            }
            val message = paramCombination.notNullString("COMMIT_MESSAGE")

            if (changes.size() <= 0) {
                message(EditorLanguage.NEED_ONE_CHANGE_AT_LEAST)
                    .send()
                return@localizationScope
            }

            message(EditorLanguage.COMMITTING_CHANGES)
                .send()

            val commit = changes.finish()
            commit.finish(submitter, message)

            val result = editor.tryAdd(commit)
            if (!result) {
                changes.unFinish()

                message(EditorLanguage.COMMIT_ERROR)
                    .send()
                return@localizationScope
            }

            message(EditorLanguage.APPLYING_CHANGES)
                .send()

            val applyResult = commit.tryApply(editor.t!!)
            if (!applyResult) {
                changes.unFinish()

                message(EditorLanguage.APPLY_ERROR)
                    .send()
                return@localizationScope
            } else {
                if (editor is AbstractAreaEditor) {
                    editor.t.manager.save()
                }
            }
            editor.newChanges()

            if (editor is AbstractAreaEditor) {
                asyncTask {
                    editor.save {
                        message(EditorLanguage.COMMIT_SAVED)
                            .send()
                    }
                }
            }
            if (editor is AbstractGameEditor) {
                asyncTask {
                    editor.save {
                        message(EditorLanguage.COMMIT_SAVED)
                            .send()
                    }
                }
            }

            message(EditorLanguage.COMMIT_FINISHED)
                .send()
        }
    }
}