package io.github.gdrfgdrf.cutebedwars.game.editing.bean

import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game.IGameContext
import io.github.gdrfgdrf.cutebedwars.game.editing.base.AbstractChange
import io.github.gdrfgdrf.cutebedwars.game.editing.exception.InoperableChangesException
import io.github.gdrfgdrf.cutebedwars.game.editing.exception.RedoException
import io.github.gdrfgdrf.cutebedwars.game.editing.exception.UndoException
import java.util.concurrent.LinkedBlockingQueue

class Changes {
    var operable = true
        private set

    val changes = arrayListOf<AbstractChange>()
    private val undoQueue = LinkedBlockingQueue<AbstractChange>()

    private fun check() {
        if (!operable) {
            throw InoperableChangesException()
        }
    }

    fun apply(gameContext: IGameContext) {
        check()
        changes.forEach {
            it.apply(gameContext)
        }
    }

    fun add(change: AbstractChange) {
        check()
        changes.add(change)
    }

    fun undo() {
        check()
        if (changes.isEmpty()) {
            throw UndoException()
        }

        val latestChange = changes[changes.size - 1]
        changes.removeAt(changes.size - 1)

        undoQueue.add(latestChange)
    }

    fun redo() {
        check()
        if (undoQueue.isEmpty()) {
            throw RedoException()
        }

        val change = undoQueue.poll()
        add(change)
    }

    fun finish() {
        operable = false
    }
}