package io.github.gdrfgdrf.cutebedwars.storage.common

import io.github.gdrfgdrf.cutebedwars.abstracts.editing.ICommit
import io.github.gdrfgdrf.cutebedwars.protobuf.storage.StorageProto

object Convertors {
    fun protobufCommit(commit: ICommit<*>): StorageProto.Commit {
        val commitBuilder = StorageProto.Commit.newBuilder()
            .setId(commit.id().toString())
            .setTime(commit.time())
            .setSubmitter(commit.submitter())
            .setMessage(commit.message())

        commit.changes().forEach { change ->
            val changeProtobuf = StorageProto.Change.newBuilder()
                .setIdentifier(change.identifier())
                .setName(change.name())
                .addAllArgs(arrayListOf<String?>().apply {
                    change.args().forEach {
                        add(it.toString())
                    }
                })
                .build()

            commitBuilder.addChanges(changeProtobuf)
        }

        return commitBuilder.build()
    }

}