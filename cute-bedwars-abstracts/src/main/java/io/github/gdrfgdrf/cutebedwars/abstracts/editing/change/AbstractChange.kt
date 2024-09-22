package io.github.gdrfgdrf.cutebedwars.abstracts.editing.change

abstract class AbstractChange<T>(val name: String) {
    abstract fun apply(t: T)
    abstract fun makeUndo(): AbstractChange<T>

}