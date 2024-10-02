package io.github.gdrfgdrf.cutebedwars.abstracts.editing

abstract class AbstractEditor<T>(val uuid: String, val t: T) {
    private val commits = arrayListOf<ICommit<T>>()
    private var currentChanges: IChanges<T>? = null

    fun currentChanges(): IChanges<T>? = currentChanges

    fun newChanges(): IChanges<T> {
        currentChanges = IChanges.new()
        return currentChanges!!
    }

    @Suppress("UNCHECKED_CAST")
    fun tryAdd(commit: ICommit<*>): Boolean {
        runCatching {
            commits.add(commit as ICommit<T>)
        }.onFailure {
            return false
        }
        return true
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

    fun type(): Class<*> = t!!::class.java
}