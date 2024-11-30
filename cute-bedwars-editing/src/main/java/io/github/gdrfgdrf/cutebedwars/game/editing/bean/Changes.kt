package io.github.gdrfgdrf.cutebedwars.game.editing.bean

import io.github.gdrfgdrf.cutebedwars.abstracts.editing.change.AbstractChange
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.IChanges
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.ICommit
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.cutebedwars.game.editing.exception.InoperableChangesException
import io.github.gdrfgdrf.cutebedwars.game.editing.exception.RedoException
import io.github.gdrfgdrf.cutebedwars.game.editing.exception.UndoException
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import java.util.concurrent.LinkedBlockingQueue

@ServiceImpl("changes")
class Changes<T> : IChanges<T> {
    var operable = true
        private set

    val changes = arrayListOf<AbstractChange<T>>()
    private val undoQueue = LinkedBlockingQueue<AbstractChange<T>>()

    override val size: Int = changes.size

    private fun check() {
        if (!operable) {
            throw InoperableChangesException()
        }
    }

    override fun apply(t: T) {
        "Applying all changes (Changes)".logInfo()
        changes.forEach {
            it.apply(t)
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun tryAdd(change: AbstractChange<*>): Boolean {
        check()
        "Trying to add a change".logInfo()

        runCatching {
            changes.add(change as AbstractChange<T>)
        }.onFailure {
            return false
        }

        return true
    }

    override fun add(change: AbstractChange<T>) {
        check()
        "Adding a change".logInfo()
        changes.add(change)
    }

    override fun tryUndo(change: AbstractChange<*>) {
        check()
        if (changes.isEmpty()) {
            throw UndoException()
        }
        val index = changes.indexOf(change)
        if (index == -1) {
            throw UndoException()
        }

        "Undoing change ${change.name()} with an exactly index".logInfo()

        changes.removeAt(index)
    }

    override fun undo() {
        check()
        if (changes.isEmpty()) {
            throw UndoException()
        }
        val latestChange = changes[changes.size - 1]

        "Undoing change ${latestChange.name()} and put it into the undo queue".logInfo()

        changes.removeAt(changes.size - 1)

        undoQueue.add(latestChange)
    }

    override fun redo() {
        check()
        if (undoQueue.isEmpty()) {
            throw RedoException()
        }

        val change = undoQueue.poll()

        "Redoing change ${change.name()}".logInfo()

        add(change)
    }

    override fun finish(): ICommit<T> {
        "Finishing changes".logInfo()
        operable = false
        return Commit(this)
    }

    override fun forEach(block: (AbstractChange<T>) -> Unit) {
        changes.forEach(block)
    }

    override fun find(id: Long): AbstractChange<T>? {
        return changes.stream()
            .filter {
                it.id == id
            }
            .findAny()
            .orElse(null)
    }
}