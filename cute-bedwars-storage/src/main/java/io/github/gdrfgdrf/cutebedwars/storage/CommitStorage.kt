package io.github.gdrfgdrf.cutebedwars.storage

import io.github.gdrfgdrf.cutebedwars.abstracts.editing.ICommit
import io.github.gdrfgdrf.cutebedwars.abstracts.storage.AbstractCommitStorage
import io.github.gdrfgdrf.cutebedwars.protobuf.storage.StorageProto.*
import io.github.gdrfgdrf.cutebedwars.utils.runAsyncTask
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import java.io.File

@ServiceImpl("commit_storage", needArgument = true)
class CommitStorage(folder: String) : AbstractCommitStorage() {
    constructor(argumentSet: ArgumentSet): this(argumentSet.args[0] as String)

    private val protobufStorage = ProtobufStorage()

    private val areaCommitFile = File(folder, "area")
    private val areaProtobuf = protobufStorage.protobuf(areaCommitFile, Commits.parser(), Commits.newBuilder()::build)

    private val gameCommitFile = File(folder, "game")
    private val gameProtobuf =
        protobufStorage.protobuf(gameCommitFile, GameCommits.parser(), GameCommits.newBuilder()::build)

    override fun saveArea(commit: ICommit<*>, finished: () -> Unit) = runAsyncTask {
        val commitProtobuf = convert(commit)
        areaProtobuf.rebuild {
            it!!.toBuilder()
                .addCommits(commitProtobuf)
                .build()
        }

        areaProtobuf.save()
        finished()
    }

    override fun saveGame(id: Long, commit: ICommit<*>, finished: () -> Unit) = runAsyncTask {
        val commitProtobuf = convert(commit)
        gameProtobuf.rebuild {
            val commits = it!!.getMapOrDefault(id.toString(), Commits.newBuilder().build())
            val newCommitsProtobuf = commits.toBuilder()
                .addCommits(commitProtobuf)
                .build()
            it.toBuilder()
                .putMap(id.toString(), newCommitsProtobuf)
                .build()
        }

        gameProtobuf.save()
        finished()
    }

    private fun convert(commit: ICommit<*>): Commit {
        val commitBuilder = Commit.newBuilder()
            .setId(commit.id().toString())
            .setTime(commit.time())
            .setSubmitter(commit.submitter())
            .setMessage(commit.message())

        commit.changes().forEach { change ->
            val changeProtobuf = Change.newBuilder()
                .setType(change.annotationName())
                .setName(change.name)
                .build()

            commitBuilder.addChanges(changeProtobuf)
        }

        return commitBuilder.build()
    }
}