package io.github.gdrfgdrf.cutebedwars.storage

import com.google.protobuf.Message
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.ICommit
import io.github.gdrfgdrf.cutebedwars.abstracts.storage.AbstractGameCommitStorage
import io.github.gdrfgdrf.cutebedwars.protobuf.storage.StorageProto.*
import io.github.gdrfgdrf.cutebedwars.storage.common.Convertors
import io.github.gdrfgdrf.cutebedwars.storage.common.ProtobufStorage
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import java.io.File

@ServiceImpl("game_commit_storage", needArgument = true)
class GameCommitStorage(folder: String) : AbstractGameCommitStorage() {
    constructor(argumentSet: ArgumentSet): this(argumentSet.args[0] as String)

    private val protobufStorage = ProtobufStorage()

    private val gameCommitFile = File(folder, "game")
    private val gameProtobuf =
        protobufStorage.protobuf(gameCommitFile, GameCommits.parser(), GameCommits.newBuilder()::build)

    override fun get(): GameCommits? = gameProtobuf.message

    override fun save(gameId: Long, commit: ICommit<*>, finished: () -> Unit) {
        val commitProtobuf = Convertors.protobufCommit(commit)
        gameProtobuf.rebuild {
            val commits = it!!.getMapOrDefault(gameId.toString(), Commits.newBuilder().build())
            val newCommitsProtobuf = commits.toBuilder()
                .addCommits(commitProtobuf)
                .build()
            it.toBuilder()
                .putMap(gameId.toString(), newCommitsProtobuf)
                .build()
        }

        gameProtobuf.save()
        finished()
    }
}