package io.github.gdrfgdrf.cutebedwars.game.editing.pojo

import com.github.yitter.idgen.YitIdHelper
import io.github.gdrfgdrf.cutebedwars.game.editing.base.AbstractChange
import io.github.gdrfgdrf.cutebedwars.game.editing.bean.Changes
import io.github.gdrfgdrf.cutebedwars.utils.extension.now

class Commit {
    private val id: Long = YitIdHelper.nextId()
    var time: String? = null
    var playerUuid: String? = null
    var message: String? = null

    private val changes = Changes()

    fun add(change: AbstractChange) {
        changes.add(change)
    }

    fun revert(): Commit {
        val revertCommit = Commit()
        revertCommit.time = now()
        revertCommit.playerUuid = playerUuid
        revertCommit.message = "revert: $id"

        changes.changes.forEach {
            val makeUndo = it.makeUndo()
            revertCommit.add(makeUndo)
        }

        return revertCommit
    }
}