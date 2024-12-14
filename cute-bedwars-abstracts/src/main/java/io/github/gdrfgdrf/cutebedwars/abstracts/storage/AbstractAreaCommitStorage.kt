package io.github.gdrfgdrf.cutebedwars.abstracts.storage

import io.github.gdrfgdrf.cutebedwars.abstracts.editing.ICommit
import io.github.gdrfgdrf.cutebedwars.protobuf.storage.StorageProto.Commits
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@Service("area_commit_storage", singleton = false)
abstract class AbstractAreaCommitStorage {
    abstract fun commits(): Commits?
    abstract fun save(commit: ICommit<*>, finished: () -> Unit)

    companion object {
        fun new(folder: String): AbstractAreaCommitStorage =
            Mediator.get(AbstractAreaCommitStorage::class.java, ArgumentSet(arrayOf(folder)))!!
    }
}