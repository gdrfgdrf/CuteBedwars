package io.github.gdrfgdrf.cutebedwars.commands.sub

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.IEditors
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.AbstractSubCommand
import io.github.gdrfgdrf.cutebedwars.commands.finder.BetterAreaFinder
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandSyntaxLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.EditorLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object EditorArea : AbstractSubCommand(
    command = ICommands.valueOf("EDITOR_AREA")
) {
    override fun syntax(): LanguageString? = CommandSyntaxLanguage.EDITOR_AREA
    override fun description(): LanguageString? = CommandDescriptionLanguage.EDITOR_AREA

    override fun run(sender: CommandSender, args: Array<String>, paramSchemeIndex: Int) {
        localizationScope(sender) {
            val findType = args[0]
            val identifier = args[1]
            val uuid = if (sender is Player) {
                sender.uniqueId.toString()
            } else {
                "not_a_player"
            }

            val areaManager = BetterAreaFinder.find(sender, findType, identifier) ?: return@localizationScope
            val editor = IEditors.instance().get(uuid)
            if (editor != null) {
                message(EditorLanguage.ALREADY_IN_EDITING_MODE)
                    .send()
                return@localizationScope
            }

            message(EditorLanguage.LOADING_AREA_EDITOR)
                .send()

            val areaEditor = IEditors.instance().createAreaEditor(uuid, areaManager.context())
            IEditors.instance().put(areaEditor)

            message(EditorLanguage.EDITOR_LOAD_FINISHED)
                .send()
        }
    }
}