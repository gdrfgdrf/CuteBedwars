package io.github.gdrfgdrf.cutebedwars.abstracts.information

import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILocalizationMessage
import io.github.gdrfgdrf.cutebedwars.protobuf.storage.StorageProto.Commit
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import org.bukkit.command.CommandSender

@Service("protobuf_commit_information")
@KotlinSingleton
interface IProtobufCommitInformation {
    fun convert(sender: CommandSender, commit: Commit): List<ILocalizationMessage>

    companion object {
        fun instance(): IProtobufCommitInformation = Mediator.get(IProtobufCommitInformation::class.java)!!
    }
}