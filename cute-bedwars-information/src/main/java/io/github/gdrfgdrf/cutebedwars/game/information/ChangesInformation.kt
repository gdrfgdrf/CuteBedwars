package io.github.gdrfgdrf.cutebedwars.game.information

import io.github.gdrfgdrf.cutebedwars.abstracts.editing.IChanges
import io.github.gdrfgdrf.cutebedwars.abstracts.information.IChangesInformation
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ITranslationAgent
import io.github.gdrfgdrf.cutebedwars.languages.collect.EditorLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import org.bukkit.command.CommandSender

@ServiceImpl("changes_information")
object ChangesInformation : IChangesInformation {
    override fun convert(sender: CommandSender, changes: IChanges<*>): List<ITranslationAgent> =
        localizationScope(sender) {
            val messages = arrayListOf<ITranslationAgent>()

            changes.forEach { change ->
                messages.add(
                    message(EditorLanguage.CHANGE_FORMAT).apply {
                        val localizedIdentifier = change.localizedIdentifier().get().string
                        val localizedName = change.localizedName()(sender).string()

                        format0(localizedIdentifier, localizedName)
                    }
                )
            }

            return@localizationScope messages
        }
}