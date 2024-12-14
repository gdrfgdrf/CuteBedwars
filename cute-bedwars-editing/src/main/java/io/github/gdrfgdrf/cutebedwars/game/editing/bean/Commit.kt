package io.github.gdrfgdrf.cutebedwars.game.editing.bean

import com.github.yitter.idgen.YitIdHelper
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.IChanges
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.ICommit
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.now
import io.github.gdrfgdrf.cutebedwars.game.editing.exception.OperableChangesException
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import org.bukkit.command.CommandSender

@Suppress("UNCHECKED_CAST")
@ServiceImpl("commit", needArgument = true)
class Commit<T>(
    override val changes: Changes<T>
) : ICommit<T> {
    constructor(argumentSet: ArgumentSet): this(argumentSet.args[0] as Changes<T>)

    init {
        if (changes.operable) {
            throw OperableChangesException()
        }
    }

    override var id: Long = YitIdHelper.nextId()
    override var time: String? = null
    override var submitter: String? = null
    override var message: String? = null

    @Suppress("UNCHECKED_CAST")
    override fun tryApply(any: Any, sender: CommandSender): Boolean {
        "Trying to apply all changes (Commit)".logInfo()
        runCatching {
            changes.apply(any as T, sender)
        }.onFailure {
            return false
        }
        return true
    }

    override fun apply(t: T, sender: CommandSender) {
        "Applying all changes (Commit)".logInfo()
        changes.apply(t, sender)
    }

    override fun revert(submitter: String): ICommit<T> {
        "Reverting a commit id: $id".logInfo()

        val newChanges = Changes<T>()
        changes.changes.reversed().forEach {
            newChanges.add(it.makeUndo())
        }

        val revertCommit = newChanges.finish()
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
}