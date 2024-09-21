package io.github.gdrfgdrf.cutebedwars.commands.finder

import io.github.gdrfgdrf.cutebedwars.abstracts.finder.IEditorFinder
import io.github.gdrfgdrf.cutebedwars.abstracts.game.editing.AbstractEditor
import org.bukkit.command.CommandSender

object BetterEditorFinder {
    fun find(sender: CommandSender): AbstractEditor<*>? {
        var editor: AbstractEditor<*>? = null

        IEditorFinder.get().find(sender) {
            editor = it
        }

        return editor
    }
}