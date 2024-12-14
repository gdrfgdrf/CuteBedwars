package io.github.gdrfgdrf.cutebedwars.storage

import io.github.gdrfgdrf.cutebedwars.abstracts.editing.ICommit
import io.github.gdrfgdrf.cutebedwars.abstracts.storage.AbstractGameCommitStorage
import io.github.gdrfgdrf.cutebedwars.protobuf.storage.StorageProto.*
import io.github.gdrfgdrf.cutebedwars.storage.common.Convertors
import io.github.gdrfgdrf.cutebedwars.storage.common.ProtobufStorage
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import java.io.File

@ServiceImpl("game_commit_storage", needArgument = true)
class GameCommitStorage(file: String) : AbstractGameCommitStorage() {
    constructor(argumentSet: ArgumentSet): this(argumentSet.args[0] as String)

    private val protobufStorage = ProtobufStorage()

    private val gameCommitFile = File(file)
    private val gameProtobuf = protobufStorage.protobuf(gameCommitFile, Commits.parser(), Commits.newBuilder())

    override fun commits(): Commits? = gameProtobuf.message

    override fun save(commit: ICommit<*>, finished: () -> Unit) {
        val commitProtobuf = Convertors.protobufCommit(commit)
        gameProtobuf.rebuild {
            it!!.toBuilder()
                .addCommits(commitProtobuf)
                .build()
        }

        gameProtobuf.save()
        finished()
    }
}