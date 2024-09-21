package io.github.gdrfgdrf.cutebedwars.abstracts.game.editing

abstract class AbstractEditor<T>(val uuid: String, val t: T) {
    private val commits = arrayListOf<ICommit<T>>()
    private var currentChanges: IChanges<T>? = null

    fun currentChanges(): IChanges<T>? = currentChanges

    fun newChanges(): IChanges<T> {
        currentChanges = IChanges.get()
        return currentChanges!!
    }

    fun add(commit: ICommit<T>) {
        commits.add(commit)
    }

    fun apply() {
        commits.forEach {
            it.apply(t)
        }
        commits.clear()
    }

    fun exit(apply: Boolean = true) {
        if (apply) {
            apply()
        }

        IEditors.get().remove(uuid)
    }
}