package io.github.gdrfgdrf.cutebedwars.game.information

import io.github.gdrfgdrf.cutebedwars.abstracts.information.IAreaInformation
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaManager
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILocalizationMessage
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ITranslationAgent
import io.github.gdrfgdrf.cutebedwars.beans.pojo.area.Area
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Status
import io.github.gdrfgdrf.cutebedwars.languages.collect.AreaManagementLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommonLanguage
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.localizationScope
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import org.bukkit.command.CommandSender

@ServiceImpl("area_information")
object AreaInformation : IAreaInformation {
    override fun convert(sender: CommandSender, areaManager: IAreaManager): List<ITranslationAgent> =
        localizationScope(sender) {
            val area = areaManager.area()
            val properties = getAeaProperties(area)
            val propertyMessages = arrayListOf<ITranslationAgent>()
            val gameMessages = arrayListOf<ITranslationAgent>()

            properties.forEach { (key, value) ->
                propertyMessages.add(
                    message(AreaManagementLanguage.AREA_PROPERTY_FORMAT)
                        .format0(key, value)
                )
            }

            if (area.games.isNullOrEmpty()) {
                gameMessages.add(
                    message(AreaManagementLanguage.AREA_GAMES_EMPTY)
                )
            } else {
                area.games.forEach {
                    val statusMessage = when (it.status) {
                        Status.DISABLED -> CommonLanguage.STATUS_DISABLED
                        Status.EDITING -> CommonLanguage.STATUS_EDITING
                        Status.ENABLED -> CommonLanguage.STATUS_ENABLED
                        Status.INDEPENDENT -> CommonLanguage.STATUS_INDEPENDENT
                        null -> CommonLanguage.STATUS_UNKNOWN
                    }

                    gameMessages.add(
                        message(AreaManagementLanguage.AREA_GAMES_FORMAT)
                            .format0(it.name, statusMessage.operate().string)
                    )
                }
            }

            val result = arrayListOf(
                message(AreaManagementLanguage.AREA_ID_IS)
                    .format0(area.id),
                message(AreaManagementLanguage.AREA_NAME_IS)
                    .format0(area.name),
                message(AreaManagementLanguage.AREA_PROPERTY_IS),
            )
            result.addAll(propertyMessages)
            result.add(message(AreaManagementLanguage.AREA_GAMES_IS))
            result.addAll(gameMessages)

            result.add(message(CommonLanguage.DESCRIPTION_TIP))

            return@localizationScope result
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