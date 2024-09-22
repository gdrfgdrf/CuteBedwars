package io.github.gdrfgdrf.cutebedwars.commands.finder

import io.github.gdrfgdrf.cutebedwars.abstracts.finder.IChangesFinder
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.IChanges
import org.bukkit.command.CommandSender

object BetterChangesFinder {
    fun find(sender: CommandSender): IChanges<*>? {
        var changes: IChanges<*>? = null

        IChangesFinder.get().find(sender) {
            changes = it
        }

        return changes
    }
}