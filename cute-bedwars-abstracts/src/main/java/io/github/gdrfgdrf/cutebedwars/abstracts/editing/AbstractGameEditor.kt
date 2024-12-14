package io.github.gdrfgdrf.cutebedwars.abstracts.editing

import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game.IGameContext
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo

abstract class AbstractGameEditor(uuid: String, val gameContext: IGameContext) : AbstractEditor<IGameContext>(uuid, gameContext) {
    @Synchronized
    fun save(allFinished: () -> Unit) {
        "Saving commits of a game editor".logInfo()
        val commitStorage = gameContext.commitStorage

        commits().forEach {
            commitStorage.save(it) {}
            allFinished()
        }

        clear()
    }
}