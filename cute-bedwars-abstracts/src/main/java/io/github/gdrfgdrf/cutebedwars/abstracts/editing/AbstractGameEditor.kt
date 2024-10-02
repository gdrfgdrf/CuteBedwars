package io.github.gdrfgdrf.cutebedwars.abstracts.editing

import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IConstants
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game.IGameContext
import io.github.gdrfgdrf.cutebedwars.abstracts.storage.AbstractGameCommitStorage

abstract class AbstractGameEditor(uuid: String, val gameContext: IGameContext) : AbstractEditor<IGameContext>(uuid, gameContext) {
    fun save(allFinished: () -> Unit) {
        val commitStorage = gameContext.commitStorage()

        commits().forEach {
            commitStorage.save(gameContext.game().id, it) {}
            allFinished()
        }
    }
}