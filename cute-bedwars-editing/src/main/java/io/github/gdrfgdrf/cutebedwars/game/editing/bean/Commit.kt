package io.github.gdrfgdrf.cutebedwars.game.editing.bean

import com.github.yitter.idgen.YitIdHelper
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.IChanges
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.ICommit
import io.github.gdrfgdrf.cutebedwars.game.editing.exception.OperableChangesException
import io.github.gdrfgdrf.cutebedwars.utils.extension.logInfo
import io.github.gdrfgdrf.cutebedwars.utils.extension.now

class Commit<T>(
    private val changes: Changes<T>
) : ICommit<T> {
    init {
        if (changes.operable) {
            throw OperableChangesException()
        }
    }

    private val id: Long = YitIdHelper.nextId()
    private var time: String? = null
    private var submitter: String? = null
    private var message: String? = null

    override fun apply(t: T) {
        changes.apply(t)
    }

    override fun revert(submitter: String): ICommit<T> {
        "Reverting commit id: $id".logInfo()

        val newChanges = Changes<T>()
        changes.changes.forEach {
            newChanges.add(it.makeUndo())
        }
        newChanges.finish()

        val revertCommit = Commit(newChanges)
        revertCommit.time = now()
        revertCommit.submitter = submitter
        revertCommit.message = "revert: $id"

        return revertCommit
    }

    override fun finish(submitter: String, message: String) {
        this.time = now()
        this.submitter = submitter
        this.message = message
    }

    override fun id(): Long = id

    override fun time(): String? = time
    override fun time(time: String) {
        this.time = time
    }

    override fun submitter(): String? = submitter
    override fun submitter(submitter: String) {
        this.submitter = submitter
    }

    override fun message(): String? = message
    override fun message(message: String) {
        this.message = message
    }

    override fun changes(): IChanges<T> = changes
}