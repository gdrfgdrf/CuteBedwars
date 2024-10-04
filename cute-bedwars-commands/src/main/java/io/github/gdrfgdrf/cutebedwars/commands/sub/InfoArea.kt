package io.github.gdrfgdrf.cutebedwars.commands.sub

import io.github.gdrfgdrf.cutebedwars.abstracts.chatpage.IChatPage
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IFindType
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPageRequestTypes
import io.github.gdrfgdrf.cutebedwars.abstracts.finder.IAreaFinder
import io.github.gdrfgdrf.cutebedwars.abstracts.information.IAreaInformation
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.IManagers
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaManager
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.AbstractSubCommand
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.toIntOrDefault
import io.github.gdrfgdrf.cutebedwars.commands.common.ParamScheme
import io.github.gdrfgdrf.cutebedwars.languages.collect.AreaManagementLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandSyntaxLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
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
                if (paramSchemeIndex == 2) {
                    args[2].toIntOrDefault(1)
                } else {
                    args[0].toIntOrDefault(1)
                }
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
                val findResult = IAreaFinder.instance().find(
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
                areaManagers.addAll(IManagers.instance().list())
                if (areaManagers.isEmpty()) {
                    message(AreaManagementLanguage.AREA_IS_EMPTY)
                        .send()
                    return@localizationScope
                }
            }

            val chatPage = IChatPage.cache(
                sender,
                IPageRequestTypes.valueOf("INFO_AREA"),
                identifier
            ) {
                return@cache arrayListOf()
            }
            areaManagers.forEach { areaManager ->
                chatPage.addPage {
                    IAreaInformation.instance().convert(sender, areaManager)
                }
            }
            chatPage.send(pageIndex - 1)
        }
    }
}