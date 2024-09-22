package io.github.gdrfgdrf.cutebedwars.abstracts.editing

interface ICommit<T> {
    fun apply(t: T)
    fun revert(playerUuid: String): ICommit<T>

}