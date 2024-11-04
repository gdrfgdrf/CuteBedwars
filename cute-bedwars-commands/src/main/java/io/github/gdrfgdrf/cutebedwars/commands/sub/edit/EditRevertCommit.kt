package io.github.gdrfgdrf.cutebedwars.commands.sub.edit

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.AbstractSubCommand
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.IParamCombination
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.AbstractAreaEditor
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.AbstractGameEditor
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.asyncTask
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.toKotlinCommit
import io.github.gdrfgdrf.cutebedwars.commands.finder.BetterEditorFinder
import io.github.gdrfgdrf.cutebedwars.commands.finder.CommitFinder
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandSyntaxLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.EditorLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object EditRevertCommit : AbstractSubCommand(
    command = ICommands.valueOf("EDIT_REVERT_COMMIT")
) {
    override fun syntax(): LanguageString? = CommandSyntaxLanguage.EDIT_REVERT_COMMIT
    override fun description(): LanguageString? = CommandDescriptionLanguage.EDIT_REVERT_COMMIT

    override fun run(sender: CommandSender, args: Array<String>, paramCombination: IParamCombination) {
        localizationScope(sender) {
            val editor = BetterEditorFinder.find(sender) ?: return@localizationScope
            val commitId = paramCombination.notNullString("COMMIT_ID")

            val testCommit = CommitFinder.find(editor, commitId)
            if (testCommit == null) {
                message(EditorLanguage.NOT_FOUND_COMMIT)
                    .send()
                return@localizationScope
            }

            val linkedCommits = CommitFinder.linkFind(editor, commitId) ?: return@localizationScope
            linkedCommits.reverse()

            val submitter = if (sender is Player) {
                "${sender.name} (${sender.uniqueId})"
            } else {
                "not_a_player"
            }

            message(EditorLanguage.REVERTING_COMMIT)
                .format0(linkedCommits.size)
                .send()

            runCatching {
                linkedCommits.forEach { commit ->
                    val kotlinCommit = commit.toKotlinCommit()
                    val revertedCommit = kotlinCommit.revert(submitter)

                    if (!editor.tryAdd(revertedCommit)) {
                        message(EditorLanguage.COMMIT_ERROR)
                            .send()
                        return@localizationScope
                    }

                    val applyResult = revertedCommit.tryApply(editor.t!!)
                    if (!applyResult) {
                        message(EditorLanguage.APPLY_ERROR)
                            .send()
                        return@localizationScope
                    }
                    editor.newChanges()

                    if (editor is AbstractAreaEditor) {
                        asyncTask {
                            editor.save {}
                        }
                    }
                    if (editor is AbstractGameEditor) {
                        asyncTask {
                            editor.save {}
                        }
                    }
                }

                message(EditorLanguage.REVERT_COMMIT_FINISHED)
                    .send()
            }.onFailure {
                it.printStackTrace()
                message(EditorLanguage.REVERT_COMMIT_ERROR)
                    .send()
            }
        }
    }
}