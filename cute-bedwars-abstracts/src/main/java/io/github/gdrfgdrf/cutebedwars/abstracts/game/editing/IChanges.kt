package io.github.gdrfgdrf.cutebedwars.abstracts.game.editing

import io.github.gdrfgdrf.cutebedwars.abstracts.game.editing.change.AbstractChange
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("changes", singleton = false)
interface IChanges<T> {
    fun apply(t: T)
    fun add(change: AbstractChange<T>)
    fun undo()
    fun redo()
    fun finish()

    companion object {
        fun <T> get(): IChanges<T> = Mediator.get(IChanges::class.java)!!
    }
}