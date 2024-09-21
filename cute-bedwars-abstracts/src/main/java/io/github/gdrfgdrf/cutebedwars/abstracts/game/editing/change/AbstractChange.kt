package io.github.gdrfgdrf.cutebedwars.abstracts.game.editing.change

abstract class AbstractChange<T>(val name: String) {
    abstract fun apply(t: T)
    abstract fun makeUndo(): AbstractChange<T>

}