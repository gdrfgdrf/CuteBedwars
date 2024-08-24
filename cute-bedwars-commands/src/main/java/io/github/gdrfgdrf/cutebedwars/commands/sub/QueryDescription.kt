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
    command = ICommands.valueOf("QUERY_DESCRIPTION")
) {
    override fun syntax(): LanguageString? = CommandSyntaxLanguage.QUERY_DESCRIPTION
    override fun description(): LanguageString? = CommandDescriptionLanguage.QUERY_DESCRIPTION

    override fun run(sender: CommandSender, args: Array<String>) {
        localizationScope(sender) {
            val raw: String
            var searchResult: List<IDescriptions>?

            if (args.isEmpty()) {
                raw = ""
                searchResult = arrayListOf()
                IDescriptions.values().forEach {
                    (searchResult as ArrayList).add(it as IDescriptions)
                }
            } else {
                raw = args[0]
                val descriptionKey = raw.uppercase().replace("-", "_")
                searchResult = IDescriptions.search(descriptionKey)
            }

            if (searchResult.isNullOrEmpty()) {
                message(CommonLanguage.NOT_FOUND_DESCRIPTION)
                    .format(raw)
                    .send()
                return@localizationScope
            }

            searchResult = searchResult.stream().filter {
                return@filter !(it.administration()
                        && !IPermissions.valueOf("QUERY_ADMINISTRATION_DESCRIPTION").hasPermission(sender))
            }.toList()

            if (searchResult.isEmpty()) {
                message(CommonLanguage.NOT_FOUND_DESCRIPTION)
                    .format(raw)
                    .send()
                return@localizationScope
            }

            searchResult.forEachIndexed { index, description ->
                val divider = message(CommonLanguage.DESCRIPTION_DIVIDER)
                if (index == 0) {
                    divider.send()
                }

                val languageString = description.value()()
                if (languageString == null) {
                    message(CommonLanguage.DESCRIPTION_ERROR)
                        .format(description.name_())
                        .send()
                    divider.send()

                    return@localizationScope
                }

                message(CommonLanguage.DESCRIPTION_FORMAT)
                    .format(description.name_(), languageString.get().string)
                    .send()
                divider.send()
            }
        }
    }

    override fun tab(sender: CommandSender, args: Array<String>): MutableList<String> {
        val result = arrayListOf<String>()

        IDescriptions.values().forEach {
            if ((it as IDescriptions).administration()) {
                if (IPermissions.valueOf("QUERY_ADMINISTRATION_DESCRIPTION").hasPermission(sender)) {
                    result.add(it.name_().lowercase())
                }
            } else {
                result.add(it.name_().lowercase())
            }
        }

        return result
    }
}