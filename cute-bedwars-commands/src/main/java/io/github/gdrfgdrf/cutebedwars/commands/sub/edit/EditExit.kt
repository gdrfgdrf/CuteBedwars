package io.github.gdrfgdrf.cutebedwars.commands.sub.edit

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.AbstractSubCommand
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.IParamCombination
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.commands.finder.BetterEditorFinder
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandSyntaxLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.EditorLanguage
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.localizationScope
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.command.CommandSender

object EditExit : AbstractSubCommand(
    command = ICommands.valueOf("EDIT_EXIT")
) {
    override fun syntax(): LanguageString? = CommandSyntaxLanguage.EDIT_EXIT
    override fun description(): LanguageString? = CommandDescriptionLanguage.EDIT_EXIT

    override fun run(sender: CommandSender, args: Array<String>, paramCombination: IParamCombination) {
        localizationScope(sender) {
            val editor = BetterEditorFinder.find(sender) ?: return@localizationScope

            message(EditorLanguage.EXITING_EDITOR)
                .send()

            editor.exit()

            message(EditorLanguage.EXIT_FINISHED)
                .send()
        }
    }
}