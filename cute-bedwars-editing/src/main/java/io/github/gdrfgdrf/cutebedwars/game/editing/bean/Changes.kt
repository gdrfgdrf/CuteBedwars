package io.github.gdrfgdrf.cutebedwars.game.editing.bean

import io.github.gdrfgdrf.cutebedwars.abstracts.editing.change.AbstractChange
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.IChanges
import io.github.gdrfgdrf.cutebedwars.game.editing.exception.InoperableChangesException
import io.github.gdrfgdrf.cutebedwars.game.editing.exception.RedoException
import io.github.gdrfgdrf.cutebedwars.game.editing.exception.UndoException
import io.github.gdrfgdrf.cutebedwars.utils.extension.logInfo
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import java.util.concurrent.LinkedBlockingQueue

@ServiceImpl("changes")
class Changes<T> : IChanges<T> {
    var operable = true
        private set

    val changes = arrayListOf<AbstractChange<T>>()
    private val undoQueue = LinkedBlockingQueue<AbstractChange<T>>()

    private fun check() {
        if (!operable) {
            throw InoperableChangesException()
        }
    }

    override fun apply(t: T) {
        check()
        "Applying all changes".logInfo()
        changes.forEach {
            it.apply(t)
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun tryAdd(change: AbstractChange<*>): Boolean {
        check()

        runCatching {
            changes.add(change as AbstractChange<T>)
        }.onFailure {
            return false
        }

        return true
    }

    override fun add(change: AbstractChange<T>) {
        check()
        changes.add(change)
    }

    override fun undo() {
        check()
        if (changes.isEmpty()) {
            throw UndoException()
        }
        val latestChange = changes[changes.size - 1]

        "Undoing change ${latestChange.name}".logInfo()

        changes.removeAt(changes.size - 1)

        undoQueue.add(latestChange)
    }

    override fun redo() {
        check()
        if (undoQueue.isEmpty()) {
            throw RedoException()
        }

        val change = undoQueue.poll()

        "Redoing change ${change.name}".logInfo()

        add(change)
    }

    override fun finish() {
        "Finishing changes".logInfo()
        operable = false
    }

    override fun size(): Int {
        return changes.size
    }

    override fun forEach(block: (AbstractChange<T>) -> Unit) {
        changes.forEach(block)
    }
}