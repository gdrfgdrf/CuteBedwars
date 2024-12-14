package io.github.gdrfgdrf.cutebedwars.abstracts.editing

import io.github.gdrfgdrf.cutebedwars.abstracts.editing.change.AbstractChange
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import org.bukkit.command.CommandSender

@Service("changes", singleton = false)
interface IChanges<T> {
    fun size(): Int
    fun apply(t: T, sender: CommandSender)
    fun tryAdd(change: AbstractChange<*>): Boolean
    fun add(change: AbstractChange<T>)
    fun tryUndo(change: AbstractChange<*>)
    fun undo()
    fun redo()
    fun finish(): ICommit<T>
    fun unFinish()

    fun forEach(block: (AbstractChange<T>) -> Unit)
    fun find(id: Long): AbstractChange<T>?

    companion object {
        fun <T> new(): IChanges<T> = Mediator.get(IChanges::class.java)!!
    }
}