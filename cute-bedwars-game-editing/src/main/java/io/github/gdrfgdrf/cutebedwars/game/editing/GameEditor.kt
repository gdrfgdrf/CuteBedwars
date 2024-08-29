package io.github.gdrfgdrf.cutebedwars.game.editing

import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game.IGameContext
import io.github.gdrfgdrf.cutebedwars.game.editing.pojo.Commit

class GameEditor(val uuid: String, val gameContext: IGameContext) {
    private val commits = arrayListOf<Commit>()

    fun add(commit: Commit) {
        commits.add(commit)
    }

    fun apply() {
        commits.forEach {
            it.apply(gameContext)
        }
    }

    fun exit(apply: Boolean = true) {
        if (apply) {
            apply()
        }

        Editors.remove(uuid)
    }


}