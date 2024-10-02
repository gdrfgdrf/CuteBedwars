package io.github.gdrfgdrf.cutebedwars.game.information

import io.github.gdrfgdrf.cutebedwars.abstracts.information.IProtobufCommitInformation
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILocalizationMessage
import io.github.gdrfgdrf.cutebedwars.languages.collect.EditorLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.cutebedwars.protobuf.storage.StorageProto.Commit
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import org.bukkit.command.CommandSender

@ServiceImpl("protobuf_commit_information")
object ProtobufCommitInformation : IProtobufCommitInformation {
    override fun convert(sender: CommandSender, commit: Commit): List<ILocalizationMessage> =
        localizationScope(sender) {
            val messages = arrayListOf<ILocalizationMessage>()

            messages.add(
                message(EditorLanguage.COMMIT_ID_IS)
                    .format(commit.id.toString())
            )
            messages.add(
                message(EditorLanguage.COMMIT_TIME_IS)
                    .format(commit.time)
            )
            messages.add(
                message(EditorLanguage.COMMIT_SUBMITTER_IS)
                    .format(commit.submitter)
            )
            messages.add(
                message(EditorLanguage.COMMIT_MESSAGE_IS)
                    .format(commit.message)
            )
            messages.add(
                message(EditorLanguage.COMMIT_CHANGES_IS)
            )
            commit.changesList.forEach { change ->
                messages.add(
                    message(EditorLanguage.COMMIT_CHANGES_FORMAT)
                        .format(change.type, change.name)
                )
            }

            messages
        }
}