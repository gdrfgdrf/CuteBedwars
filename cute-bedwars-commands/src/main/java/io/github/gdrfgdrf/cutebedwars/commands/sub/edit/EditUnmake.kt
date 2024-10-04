package io.github.gdrfgdrf.cutebedwars.commands.sub.edit

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.AbstractSubCommand
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.change.AbstractChange
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.commands.finder.BetterChangesFinder
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandSyntaxLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.EditorLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.command.CommandSender

object EditUnmake : AbstractSubCommand(
    command = ICommands.valueOf("EDIT_UNMAKE")
) {
    override fun syntax(): LanguageString? = CommandSyntaxLanguage.EDIT_UNMAKE
    override fun description(): LanguageString? = CommandDescriptionLanguage.EDIT_UNMAKE

    override fun run(sender: CommandSender, args: Array<String>, paramSchemeIndex: Int) {
        localizationScope(sender) {
            val changes = BetterChangesFinder.find(sender) ?: return@localizationScope

            val needUnmakeChanges = arrayListOf<AbstractChange<*>>()

            args.forEach { providedId ->
                val change = changes.find(providedId.toLong())
                if (change == null) {
                    message(EditorLanguage.NOT_FOUND_CHANGE)
                        .format0(providedId)
                        .send()
                    return@localizationScope
                } else {
                    needUnmakeChanges.add(change)
                }
            }

            message(EditorLanguage.UNMAKING_CHANGE)
                .format0(args.size)
                .send()

            runCatching {
                needUnmakeChanges.forEach { change ->
                    changes.tryUndo(change)
                }

                message(EditorLanguage.UNMAKING_CHANGE_FINISHED)
                    .send()
            }.onFailure {
                it.printStackTrace()
                message(EditorLanguage.UNMAKING_CHANGE_ERROR)
                    .send()
            }
        }
    }
}