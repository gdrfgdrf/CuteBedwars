package io.github.gdrfgdrf.cutebedwars.game.editing.base

abstract class AbstractChange(name: String) {
    abstract fun makeUndo(): AbstractChange
}