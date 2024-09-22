package io.github.gdrfgdrf.cutebedwars.game.editing.bean

import com.github.yitter.idgen.YitIdHelper
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.ICommit
import io.github.gdrfgdrf.cutebedwars.game.editing.exception.OperableChangesException
import io.github.gdrfgdrf.cutebedwars.utils.extension.logInfo
import io.github.gdrfgdrf.cutebedwars.utils.extension.now

class Commit<T>(
    val changes: Changes<T>
) : ICommit<T> {
    init {
        if (changes.operable) {
            throw OperableChangesException()
        }
    }

    private val id: Long = YitIdHelper.nextId()
    var time: String? = null
    var playerUuid: String? = null
    var message: String? = null

    override fun apply(t: T) {
        changes.apply(t)
    }

    override fun revert(playerUuid: String): ICommit<T> {
        "Reverting commit id: $id".logInfo()

        val newChanges = Changes<T>()
        changes.changes.forEach {
            newChanges.add(it.makeUndo())
        }
        newChanges.finish()

        val revertCommit = Commit(newChanges)
        revertCommit.time = now()
        revertCommit.playerUuid = playerUuid
        revertCommit.message = "revert: $id"

        return revertCommit
    }
}