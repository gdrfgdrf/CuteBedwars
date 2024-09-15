package io.github.gdrfgdrf.cutebedwars.commands.sub

import io.github.gdrfgdrf.cutebedwars.abstracts.chatpage.IChatPage
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IFindType
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPageRequestTypes
import io.github.gdrfgdrf.cutebedwars.abstracts.finder.IAreaFinder
import io.github.gdrfgdrf.cutebedwars.abstracts.game.information.IAreaInformation
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.IManagers
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaManager
import io.github.gdrfgdrf.cutebedwars.beans.pojo.area.Area
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.AbstractSubCommand
import io.github.gdrfgdrf.cutebedwars.commands.common.ParamScheme
import io.github.gdrfgdrf.cutebedwars.languages.collect.AreaManagementLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandSyntaxLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.cutebedwars.utils.extension.toIntOrDefault
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.command.CommandSender

object InfoArea : AbstractSubCommand(
    command = ICommands.valueOf("INFO_AREA")
) {
    override fun syntax(): LanguageString? = CommandSyntaxLanguage.INFO_AREA
    override fun description(): LanguageString? = CommandDescriptionLanguage.INFO_AREA

    override fun run(sender: CommandSender, args: Array<String>, paramSchemeIndex: Int) {
        localizationScope(sender) {
            val findType: String
            val identifier: String
            val pageIndex = if (paramSchemeIndex == 2 || paramSchemeIndex == 0) {
                args[2].toIntOrDefault(1)
            } else {
                1
            }
            if (paramSchemeIndex != 0 && paramSchemeIndex != ParamScheme.NO_MATCH) {
                findType = args[0]
                identifier = args[1]
            } else {
                findType = ""
                identifier = ""
            }

            val areaManagers = arrayListOf<IAreaManager>()
            if (paramSchemeIndex != 0 && paramSchemeIndex != ParamScheme.NO_MATCH) {
                val findResult = IAreaFinder.get().find(
                    sender,
                    IFindType.find(findType),
                    identifier
                ) {
                    areaManagers.add(it)
                }
                if (!findResult.found()) {
                    return@localizationScope
                }
            } else {
                areaManagers.addAll(IManagers.get().list())
                if (areaManagers.isEmpty()) {
                    message(AreaManagementLanguage.AREA_IS_EMPTY)
                        .send()
                    return@localizationScope
                }
            }

            val chatPage = IChatPage.get(
                sender,
                IPageRequestTypes.valueOf("INFO_AREA"),
                identifier
            ) {
                return@get arrayListOf()
            }
            areaManagers.forEach { areaManager ->
                chatPage.addPage {
                    IAreaInformation.get().convert(sender, areaManager)
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