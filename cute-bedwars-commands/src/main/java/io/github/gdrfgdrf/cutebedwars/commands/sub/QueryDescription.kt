package io.github.gdrfgdrf.cutebedwars.commands.sub

import io.github.gdrfgdrf.cutebedwars.abstracts.chatpage.IChatPage
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IDescriptions
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPageRequestTypes
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPermissions
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.AbstractSubCommand
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.toIntOrDefault
import io.github.gdrfgdrf.cutebedwars.commands.common.ParamScheme
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandSyntaxLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommonLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.command.CommandSender

object QueryDescription : AbstractSubCommand(
    command = ICommands.valueOf("QUERY_DESCRIPTION")
) {
    override fun syntax(): LanguageString? = CommandSyntaxLanguage.QUERY_DESCRIPTION
    override fun description(): LanguageString? = CommandDescriptionLanguage.QUERY_DESCRIPTION

    override fun run(sender: CommandSender, args: Array<String>, paramSchemeIndex: Int) {
        localizationScope(sender) {
            var raw = ""
            var pageIndex = 1
            val searchResult: List<IDescriptions>?

            if (args.isEmpty() || paramSchemeIndex == ParamScheme.NO_MATCH) {
                searchResult = all(sender)
            } else {
                if (paramSchemeIndex != 0 && paramSchemeIndex != 1 && paramSchemeIndex != 2) {
                    return@localizationScope
                }

                if (paramSchemeIndex == 0) {
                    pageIndex = args[0].toIntOrDefault(1)
                    searchResult = all(sender)
                } else {
                    if (paramSchemeIndex == 1) {
                        raw = args[0]

                        val descriptionKey = raw.uppercase().replace("-", "_")
                        searchResult = IDescriptions.search(descriptionKey)
                    } else {
                        raw = args[0]
                        pageIndex = args[1].toIntOrDefault(1)

                        val descriptionKey = raw.uppercase().replace("-", "_")
                        searchResult = IDescriptions.search(descriptionKey)
                    }
                }
            }

            if (searchResult.isNullOrEmpty()) {
                message(CommonLanguage.NOT_FOUND_DESCRIPTION)
                    .format0(raw)
                    .send()
                return@localizationScope
            }

            val chatPage = IChatPage.cache(sender, IPageRequestTypes.valueOf("DESCRIPTIONS"), raw) {
                searchResult.stream()
                    .filter {
                        return@filter !(it.administration()
                                && !IPermissions.valueOf("QUERY_ADMINISTRATION_DESCRIPTION").hasPermission(sender))
                    }.map {
                        val languageString = it.value()()
                        if (languageString == null) {
                            message(CommonLanguage.DESCRIPTION_ERROR)
                                .format0(it.name_())
                        } else {
                            message(CommonLanguage.DESCRIPTION_FORMAT)
                                .format0(it.name_(), languageString.get().string)
                        }
                    }.toList()
            }
            chatPage.send(pageIndex - 1)
        }
    }

    private fun all(sender: CommandSender): List<IDescriptions> {
        var result: MutableList<IDescriptions> = arrayListOf()

        val searchResult = IDescriptions.values()
        searchResult.forEach {
            result.add(it as IDescriptions)
        }

        result = result.stream().filter {
            return@filter !(it.administration()
                    && !IPermissions.valueOf("QUERY_ADMINISTRATION_DESCRIPTION").hasPermission(sender))
        }.toList()

        return result
    }
}