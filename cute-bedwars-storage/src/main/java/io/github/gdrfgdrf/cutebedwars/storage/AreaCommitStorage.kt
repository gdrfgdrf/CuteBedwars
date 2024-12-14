package io.github.gdrfgdrf.cutebedwars.storage

import io.github.gdrfgdrf.cutebedwars.abstracts.editing.ICommit
import io.github.gdrfgdrf.cutebedwars.abstracts.storage.AbstractAreaCommitStorage
import io.github.gdrfgdrf.cutebedwars.protobuf.storage.StorageProto.*
import io.github.gdrfgdrf.cutebedwars.storage.common.Convertors
import io.github.gdrfgdrf.cutebedwars.storage.common.ProtobufStorage
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import java.io.File

@ServiceImpl("area_commit_storage", needArgument = true)
class AreaCommitStorage(folder: String) : AbstractAreaCommitStorage() {
    constructor(argumentSet: ArgumentSet): this(argumentSet.args[0] as String)

    private val protobufStorage = ProtobufStorage()

    private val areaCommitFile = File(folder, "area")
    private val areaProtobuf = protobufStorage.protobuf(areaCommitFile, Commits.parser(), Commits.newBuilder())

    override fun commits(): Commits? = areaProtobuf.message

    override fun save(commit: ICommit<*>, finished: () -> Unit) {
        val commitProtobuf = Convertors.protobufCommit(commit)
        areaProtobuf.rebuild {
            it!!.toBuilder()
                .addCommits(commitProtobuf)
                .build()
        }

        areaProtobuf.save()
        finished()
    }
}