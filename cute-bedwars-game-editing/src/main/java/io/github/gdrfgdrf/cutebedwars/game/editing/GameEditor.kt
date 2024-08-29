package io.github.gdrfgdrf.cutebedwars.game.editing

import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game.IGameContext
import io.github.gdrfgdrf.cutebedwars.game.editing.base.AbstractChange
import io.github.gdrfgdrf.cutebedwars.game.editing.pojo.Commit

class GameEditor(val gameContext: IGameContext) {
    private val commits = arrayListOf<Commit>()



    fun exit(save: Boolean = true) {

    }


}