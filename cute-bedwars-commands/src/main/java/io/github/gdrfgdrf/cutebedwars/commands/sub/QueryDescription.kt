package io.github.gdrfgdrf.cutebedwars.commands.sub

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IDescriptions
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPermissions
import io.github.gdrfgdrf.cutebedwars.commands.base.SubCommand
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandSyntaxLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommonLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.command.CommandSender

object QueryDescription : SubCommand(
    command = ICommands.get("QUERY_DESCRIPTION")
) {
    override fun syntax(): LanguageString? = CommandSyntaxLanguage.QUERY_DESCRIPTION
    override fun description(): LanguageString? = CommandDescriptionLanguage.QUERY_DESCRIPTION

    override fun run(sender: CommandSender, args: Array<String>) {
        localizationScope(sender) {
            val descriptionKey = args[0].uppercase()
            val description = IDescriptions.find(descriptionKey)

            if (description == null) {
                message(CommonLanguage.NOT_FOUND_DESCRIPTION)
                    .format(descriptionKey.lowercase())
                    .send()
                return@localizationScope
            }
            val languageString = description.value()()
            if (languageString == null) {
                message(CommonLanguage.DESCRIPTION_ERROR)
                    .send()
                return@localizationScope
            }

            if (description.administration()) {
                if (IPermissions.get("QUERY_ADMINISTRATION_DESCRIPTION").hasPermission(sender)) {
                    message(CommonLanguage.DESCRIPTION_FORMAT)
                        .format(descriptionKey, languageString.get().string)
                        .send()
                } else {
                    message(CommonLanguage.NOT_FOUND_DESCRIPTION)
                        .format(descriptionKey.lowercase())
                        .send()
                }
            } else {
                message(CommonLanguage.DESCRIPTION_FORMAT)
                    .format(descriptionKey, languageString.get().string)
                    .send()
            }
        }
    }
}