package io.github.gdrfgdrf.cutebedwars.abstracts.editing

import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game.IGameContext

abstract class AbstractGameEditor(uuid: String, val gameContext: IGameContext) : AbstractEditor<IGameContext>(uuid, gameContext) {
    @Synchronized
    fun save(allFinished: () -> Unit) {
        val commitStorage = gameContext.commitStorage()

        commits().forEach {
            commitStorage.save(gameContext.game().id, it) {}
            allFinished()
        }

        clear()
    }
}