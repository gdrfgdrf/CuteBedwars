package io.github.gdrfgdrf.cutebedwars.commands.sub

import io.github.gdrfgdrf.cutebedwars.abstracts.chatpage.IChatPage
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IDescriptions
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPageRequestTypes
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPermissions
import io.github.gdrfgdrf.cutebedwars.commands.base.SubCommand
import io.github.gdrfgdrf.cutebedwars.commands.common.ParamScheme
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandSyntaxLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommonLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.cutebedwars.utils.extension.toIntOrDefault
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.command.CommandSender

object QueryDescription : SubCommand(
    command = ICommands.valueOf("QUERY_DESCRIPTION")
) {
    override fun syntax(): LanguageString? = CommandSyntaxLanguage.QUERY_DESCRIPTION
    override fun description(): LanguageString? = CommandDescriptionLanguage.QUERY_DESCRIPTION

    override fun run(sender: CommandSender, args: Array<String>, pageSchemeIndex: Int) {
        localizationScope(sender) {
            var raw = ""
            var pageIndex = 1
            val searchResult: List<IDescriptions>?

            if (args.isEmpty() || pageSchemeIndex == ParamScheme.NO_MATCH) {
                searchResult = all(sender)
            } else {
                if (pageSchemeIndex != 0 && pageSchemeIndex != 1 && pageSchemeIndex != 2) {
                    return@localizationScope
                }

                if (pageSchemeIndex == 0) {
                    pageIndex = args[0].toIntOrDefault(1)
                    searchResult = all(sender)
                } else {
                    if (pageSchemeIndex == 1) {
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
                    .format(raw)
                    .send()
                return@localizationScope
            }

            val chatPage = IChatPage.get(sender, IPageRequestTypes.valueOf("DESCRIPTIONS"), raw) {
                searchResult.stream().map {
                    val languageString = it.value()()
                    if (languageString == null) {
                        message(CommonLanguage.DESCRIPTION_ERROR)
                            .format(it.name_())
                    } else {
                        message(CommonLanguage.DESCRIPTION_FORMAT)
                            .format(it.name_(), languageString.get().string)
                    }
                }.toList()
            }
            chatPage.send(pageIndex - 1)

//            searchResult.forEachIndexed { index, description ->
//                val divider = message(CommonLanguage.DESCRIPTION_DIVIDER)
//                if (index == 0) {
//                    divider.send()
//                }
//
//                val languageString = description.value()()
//                if (languageString == null) {
//                    message(CommonLanguage.DESCRIPTION_ERROR)
//                        .format(description.name_())
//                        .send()
//                    divider.send()
//
//                    return@localizationScope
//                }
//
//                message(CommonLanguage.DESCRIPTION_FORMAT)
//                    .format(description.name_(), languageString.get().string)
//                    .send()
//                divider.send()
//            }
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