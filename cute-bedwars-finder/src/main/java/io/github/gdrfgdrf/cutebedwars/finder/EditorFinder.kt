package io.github.gdrfgdrf.cutebedwars.finder

import io.github.gdrfgdrf.cutebedwars.abstracts.finder.IEditorFinder
import io.github.gdrfgdrf.cutebedwars.abstracts.finder.IFindResult
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.AbstractEditor
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.IEditors
import io.github.gdrfgdrf.cutebedwars.finder.result.FindResult
import io.github.gdrfgdrf.cutebedwars.languages.collect.EditorLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@ServiceImpl("editor_finder")
object EditorFinder : IEditorFinder {
    override fun find(sender: CommandSender, message: Boolean, onFound: (AbstractEditor<*>) -> Unit): IFindResult {
        val findResult = FindResult()
        val uuid = if (sender is Player) {
            sender.uniqueId.toString()
        } else {
            "not_a_player"
        }

        val editor = IEditors.get().get(uuid)
        if (editor == null) {
            if (message) {
                localizationScope(sender) {
                    message(EditorLanguage.NOT_IN_EDITING_MODE)
                        .send()
                }
            }
        } else {
            findResult.found(true)
            onFound(editor)
        }

        return findResult
    }
}