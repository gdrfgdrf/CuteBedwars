package io.github.gdrfgdrf.cutebedwars.game.information

import io.github.gdrfgdrf.cutebedwars.abstracts.information.IProtobufCommitInformation
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ITranslationAgent
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.toKotlinChange
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommonLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.EditorLanguage
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.localizationScope
import io.github.gdrfgdrf.cutebedwars.protobuf.storage.StorageProto.Commit
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import org.bukkit.command.CommandSender

@ServiceImpl("protobuf_commit_information")
object ProtobufCommitInformation : IProtobufCommitInformation {
    override fun convert(sender: CommandSender, commit: Commit): List<ITranslationAgent> =
        localizationScope(sender) {
            val messages = arrayListOf<ITranslationAgent>()

            messages.add(
                message(EditorLanguage.COMMIT_ID_IS)
                    .format0(commit.id.toString())
            )
            messages.add(
                message(EditorLanguage.COMMIT_TIME_IS)
                    .format0(commit.time)
            )
            messages.add(
                message(EditorLanguage.COMMIT_SUBMITTER_IS)
                    .format0(commit.submitter)
            )
            messages.add(
                message(EditorLanguage.COMMIT_MESSAGE_IS)
                    .format0(commit.message.ifBlank { text(CommonLanguage.NONE).string() })
            )
            messages.add(
                message(EditorLanguage.COMMIT_CHANGES_IS)
            )
            commit.changesList.forEach { change ->
                messages.add(
                    message(EditorLanguage.COMMIT_CHANGES_FORMAT).apply {
                        val kotlinChange = change.toKotlinChange()
                        val localizedIdentifier = kotlinChange.localizedIdentifier().operate().string
                        val localizedName = kotlinChange.localizedName()(sender).string()

                        format0(localizedIdentifier, localizedName)
                    }
                )
            }

            messages
        }
}