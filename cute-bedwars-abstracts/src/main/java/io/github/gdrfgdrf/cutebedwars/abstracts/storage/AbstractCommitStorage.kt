package io.github.gdrfgdrf.cutebedwars.abstracts.storage

import io.github.gdrfgdrf.cutebedwars.abstracts.editing.ICommit
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@Service("commit_storage", singleton = false)
abstract class AbstractCommitStorage {
    abstract fun saveArea(commit: ICommit<*>, finished: () -> Unit)
    abstract fun saveGame(id: Long, commit: ICommit<*>, finished: () -> Unit)

    companion object {
        fun new(folder: String): AbstractCommitStorage =
            Mediator.get(AbstractCommitStorage::class.java, ArgumentSet(arrayOf(folder)))!!
    }
}