package io.github.gdrfgdrf.cutebedwars.game.information

import io.github.gdrfgdrf.cutebedwars.abstracts.editing.IChanges
import io.github.gdrfgdrf.cutebedwars.abstracts.information.IChangesInformation
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILocalizationMessage
import io.github.gdrfgdrf.cutebedwars.languages.collect.EditorLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import org.bukkit.command.CommandSender

@ServiceImpl("changes_information")
object ChangesInformation : IChangesInformation {
    override fun convert(sender: CommandSender, changes: IChanges<*>): List<ILocalizationMessage> =
        localizationScope(sender) {
            val messages = arrayListOf<ILocalizationMessage>()

            changes.forEach {
                messages.add(
                    message(EditorLanguage.CHANGE_FORMAT)
                        .format(it.annotationName(), it.name)
                )
            }

            return@localizationScope messages
        }
}