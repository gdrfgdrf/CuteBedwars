package io.github.gdrfgdrf.cutebedwars.abstracts.game.editing.change

interface IChangeClassHolder<T : AbstractChange<*>> {
    fun create(vararg any: Any): T
}