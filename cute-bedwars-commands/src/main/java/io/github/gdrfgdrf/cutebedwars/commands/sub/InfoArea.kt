package io.github.gdrfgdrf.cutebedwars.commands.sub

import io.github.gdrfgdrf.cutebedwars.abstracts.chatpage.IChatPage
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPageRequestTypes
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.IAreaManager
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.IManagers
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILocalizationMessage
import io.github.gdrfgdrf.cutebedwars.beans.pojo.area.Area
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Status
import io.github.gdrfgdrf.cutebedwars.commands.base.SubCommand
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandSyntaxLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommonLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.cutebedwars.utils.extension.toIntOrDefault
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.command.CommandSender
import java.util.Arrays
import java.util.Properties

object InfoArea : SubCommand(
    command = ICommands.valueOf("INFO_AREA")
){
    override fun syntax(): LanguageString? = CommandSyntaxLanguage.INFO_AREA
    override fun description(): LanguageString? = CommandDescriptionLanguage.INFO_AREA

    override fun run(sender: CommandSender, args: Array<String>, pageSchemeIndex: Int) {
        localizationScope(sender) {
            val searchType = args[0]
            val identifier = args[1]
            val pageIndex = if (pageSchemeIndex == 1) {
                args[2].toIntOrDefault(1)
            } else {
                1
            }

            val areaManagers = arrayListOf<IAreaManager>()

            if (searchType == "by-id") {
               IManagers.get().get(identifier.toLong())?.let {
                   areaManagers.add(it)
               }
            }
            if (searchType == "by-name") {
                IManagers.get().get(identifier)?.forEach {
                    areaManagers.add(it)
                }
            }
            if (areaManagers.isEmpty()) {
                val message = if (searchType == "by-id") {
                    message(CommonLanguage.NOT_FOUND_AREA_BY_ID)
                        .format(identifier)
                } else {
                    message(CommonLanguage.NOT_FOUND_AREA_BY_NAME)
                        .format(identifier)
                }

                message.send()
                return@localizationScope
            }

            val chatPage = IChatPage.get(sender, IPageRequestTypes.valueOf("INFO_AREA"), identifier) {
                return@get arrayListOf()
            }
            areaManagers.forEach { areaManager ->
                val area = areaManager.area()

                chatPage.addPage {
                    val properties = getAeaProperties(area)
                    val propertyMessages = arrayListOf<ILocalizationMessage>()
                    val gameMessages = arrayListOf<ILocalizationMessage>()

                    properties.forEach { (key, value) ->
                        propertyMessages.add(
                            message(CommonLanguage.AREA_PROPERTY_FORMAT)
                                .format(key, value)
                        )
                    }

                    if (area.games.isNullOrEmpty()) {
                        gameMessages.add(
                            message(CommonLanguage.AREA_GAMES_EMPTY)
                        )
                    } else {
                        area.games.forEach {
                            val statusMessage: LanguageString = when (it.status) {
                                Status.DISABLED -> CommonLanguage.STATUS_DISABLED
                                Status.EDITING -> CommonLanguage.STATUS_EDITING
                                Status.ENABLED -> CommonLanguage.STATUS_ENABLED
                                Status.INDEPENDENT -> CommonLanguage.STATUS_INDEPENDENT
                                null -> CommonLanguage.STATUS_UNKNOWN
                            }

                            gameMessages.add(
                                message(CommonLanguage.AREA_GAMES_FORMAT)
                                    .format(it.name, statusMessage.get().string)
                            )
                        }
                    }

                    val result = arrayListOf<ILocalizationMessage>(
                        message(CommonLanguage.AREA_ID_IS)
                            .format(area.id),
                        message(CommonLanguage.AREA_NAME_IS)
                            .format(area.name),
                        message(CommonLanguage.AREA_PROPERTY_IS),
                    )
                    result.addAll(propertyMessages)
                    result.add(message(CommonLanguage.AREA_GAMES_IS))
                    result.addAll(gameMessages)

                    result
                }
            }
            chatPage.send(pageIndex - 1)
        }
    }

    private fun getAeaProperties(area: Area): Map<String, Any> {
        val result = LinkedHashMap<String, Any>()

        result["default-template-id"] = area.defaultTemplateId
        result["status"] = area.status
        result["world-name"] = area.worldName
        result["lobby-world-name"] = area.lobbyWorldName
        result["lobby-spawnpoint-coordinate"] = area.lobbySpawnpointCoordinate

        return result
    }
}