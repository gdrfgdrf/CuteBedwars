package io.github.gdrfgdrf.cutebedwars.abstracts.finder

import io.github.gdrfgdrf.cutebedwars.abstracts.editing.AbstractEditor
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import org.bukkit.command.CommandSender

@Service("editor_finder")
@KotlinSingleton
interface IEditorFinder {
    fun find(sender: CommandSender, onFound: (AbstractEditor<*>) -> Unit): IFindResult

    companion object {
        fun get(): IEditorFinder = Mediator.get(IEditorFinder::class.java)!!
    }
}