package io.github.gdrfgdrf.cutebedwars.abstracts.storage

import io.github.gdrfgdrf.cutebedwars.abstracts.editing.ICommit
import io.github.gdrfgdrf.cutebedwars.protobuf.storage.StorageProto.GameCommits
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@Service("game_commit_storage", singleton = false)
abstract class AbstractGameCommitStorage {
    abstract fun get(): GameCommits?
    abstract fun save(gameId: Long, commit: ICommit<*>, finished: () -> Unit)

    companion object {
        fun new(folder: String): AbstractGameCommitStorage = Mediator.get(
            AbstractGameCommitStorage::class.java, ArgumentSet(
                arrayOf(folder)
            )
        )!!
    }
}