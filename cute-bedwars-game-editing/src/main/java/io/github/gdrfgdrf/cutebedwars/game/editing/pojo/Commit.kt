package io.github.gdrfgdrf.cutebedwars.game.editing.pojo

import com.github.yitter.idgen.YitIdHelper
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game.IGameContext
import io.github.gdrfgdrf.cutebedwars.game.editing.bean.Changes
import io.github.gdrfgdrf.cutebedwars.game.editing.exception.OperableChangesException
import io.github.gdrfgdrf.cutebedwars.utils.extension.now

class Commit(
    val changes: Changes
) {
    init {
        if (changes.operable) {
            throw OperableChangesException()
        }
    }

    private val id: Long = YitIdHelper.nextId()
    var time: String? = null
    var playerUuid: String? = null
    var message: String? = null

    fun apply(gameContext: IGameContext) {
        changes.apply(gameContext)
    }

    fun revert(playerUuid: String): Commit {
        val newChanges = Changes()
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