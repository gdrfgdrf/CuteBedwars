package io.github.gdrfgdrf.cutebedwars.game.editing.base

import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game.IGameContext

abstract class AbstractChange(name: String) {
    abstract fun apply(gameContext: IGameContext)
    abstract fun makeUndo(): AbstractChange
}