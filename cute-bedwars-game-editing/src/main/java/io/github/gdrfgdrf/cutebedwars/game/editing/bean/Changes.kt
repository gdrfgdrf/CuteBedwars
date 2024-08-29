package io.github.gdrfgdrf.cutebedwars.game.editing.bean

import io.github.gdrfgdrf.cutebedwars.game.editing.base.AbstractChange
import io.github.gdrfgdrf.cutebedwars.game.editing.exception.RedoException
import io.github.gdrfgdrf.cutebedwars.game.editing.exception.UndoException
import java.util.concurrent.LinkedBlockingQueue

class Changes {
    val changes = arrayListOf<AbstractChange>()
    private val undoQueue = LinkedBlockingQueue<AbstractChange>()

    fun add(change: AbstractChange) {
        changes.add(change)
    }

    fun undo() {
        if (changes.isEmpty()) {
            throw UndoException()
        }

        val latestChange = changes[changes.size - 1]
        changes.removeAt(changes.size - 1)

        undoQueue.add(latestChange)
    }

    fun redo() {
        if (undoQueue.isEmpty()) {
            throw RedoException()
        }

        val change = undoQueue.poll()
        add(change)
    }
}