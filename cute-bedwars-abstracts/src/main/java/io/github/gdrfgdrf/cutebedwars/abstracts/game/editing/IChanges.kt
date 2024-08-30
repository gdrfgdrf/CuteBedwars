package io.github.gdrfgdrf.cutebedwars.abstracts.game.editing

interface IChanges<T> {
    fun apply(t: T)
    fun add(change: AbstractChange<T>)
    fun undo()
    fun redo()
    fun finish()
}