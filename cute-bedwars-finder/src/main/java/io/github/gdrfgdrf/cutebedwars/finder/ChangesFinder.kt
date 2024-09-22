package io.github.gdrfgdrf.cutebedwars.finder

import io.github.gdrfgdrf.cutebedwars.abstracts.finder.IChangesFinder
import io.github.gdrfgdrf.cutebedwars.abstracts.finder.IFindResult
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.AbstractEditor
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.IChanges
import io.github.gdrfgdrf.cutebedwars.finder.result.FindResult
import io.github.gdrfgdrf.cutebedwars.languages.collect.EditorLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import org.bukkit.command.CommandSender

@ServiceImpl("changes_finder")
object ChangesFinder : IChangesFinder {
    override fun find(sender: CommandSender, onFound: (IChanges<*>) -> Unit): IFindResult {
        val findResult = FindResult()

        var editor: AbstractEditor<*>? = null
        EditorFinder.find(sender) {
            editor = it
        }
        editor ?: return findResult


        if (editor!!.currentChanges() == null) {
            localizationScope(sender) {
                message(EditorLanguage.CHANGE_LIST_IS_NULL)
                    .send()
            }
        } else {
            findResult.found(true)
            onFound(editor!!.currentChanges()!!)
        }

        return findResult
    }
}