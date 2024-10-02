package io.github.gdrfgdrf.cutebedwars.abstracts.editing

import io.github.gdrfgdrf.cutebedwars.abstracts.editing.change.AbstractChange
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("changes", singleton = false)
interface IChanges<T> {
    fun apply(t: T)
    fun tryAdd(change: AbstractChange<*>): Boolean
    fun add(change: AbstractChange<T>)
    fun undo()
    fun redo()
    fun finish(): ICommit<T>

    fun size(): Int
    fun forEach(block: (AbstractChange<T>) -> Unit)

    companion object {
        fun <T> new(): IChanges<T> = Mediator.get(IChanges::class.java)!!
    }
}