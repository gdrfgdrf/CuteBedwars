package io.github.gdrfgdrf.cutebedwars.game.editing.bean

import com.github.yitter.idgen.YitIdHelper
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.IChanges
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.ICommit
import io.github.gdrfgdrf.cutebedwars.game.editing.exception.OperableChangesException
import io.github.gdrfgdrf.cutebedwars.utils.extension.logInfo
import io.github.gdrfgdrf.cutebedwars.utils.extension.now
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@Suppress("UNCHECKED_CAST")
@ServiceImpl("commit", needArgument = true)
class Commit<T>(
    private val changes: Changes<T>
) : ICommit<T> {
    constructor(argumentSet: ArgumentSet): this(argumentSet.args[0] as Changes<T>)

    init {
        if (changes.operable) {
            throw OperableChangesException()
        }
    }

    private var id: Long = YitIdHelper.nextId()
    private var time: String? = null
    private var submitter: String? = null
    private var message: String? = null

    @Suppress("UNCHECKED_CAST")
    override fun tryApply(any: Any): Boolean {
        runCatching {
            changes.apply(any as T)
        }.onFailure {
            return false
        }
        return true
    }

    override fun apply(t: T) {
        changes.apply(t)
    }

    override fun revert(submitter: String): ICommit<T> {
        "Reverting commit id: $id".logInfo()

        val newChanges = Changes<T>()
        changes.changes.reversed().forEach {
            newChanges.add(it.makeUndo())
        }

        val revertCommit = newChanges.finish()
        revertCommit.time(now())
        revertCommit.submitter(submitter)
        revertCommit.message("revert: $id")

        return revertCommit
    }

    override fun finish(submitter: String, message: String) {
        this.time = now()
        this.submitter = submitter
        this.message = message
    }

    override fun id(): Long = id
    override fun id(id: Long) {
        this.id = id
    }

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