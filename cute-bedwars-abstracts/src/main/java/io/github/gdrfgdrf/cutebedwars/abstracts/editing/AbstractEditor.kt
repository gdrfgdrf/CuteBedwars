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

    fun exit() {
        commits.clear()
        IEditors.instance().remove(uuid)
    }

    fun commits(): List<ICommit<T>> = commits
    fun type(): Class<*> = t!!::class.java
}