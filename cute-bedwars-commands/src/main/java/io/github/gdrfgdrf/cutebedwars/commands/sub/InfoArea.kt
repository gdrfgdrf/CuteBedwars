package io.github.gdrfgdrf.cutebedwars.commands.sub

import io.github.gdrfgdrf.cutebedwars.abstracts.chatpage.IChatPages
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPageRequestTypes
import io.github.gdrfgdrf.cutebedwars.abstracts.finder.IAreaFinder
import io.github.gdrfgdrf.cutebedwars.abstracts.information.IAreaInformation
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.IManagers
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaManager
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.AbstractSubCommand
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.IParamCombination
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILanguageString
import io.github.gdrfgdrf.cutebedwars.languages.collect.AreaManagementLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandSyntaxLanguage
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.localizationScope
import org.bukkit.command.CommandSender

object InfoArea : AbstractSubCommand(
    command = ICommands.valueOf("INFO_AREA")
) {
    override val syntax: ILanguageString = CommandSyntaxLanguage.INFO_AREA
    override val description: ILanguageString = CommandDescriptionLanguage.INFO_AREA

    override fun run(sender: CommandSender, args: Array<String>, paramCombination: IParamCombination) {
        localizationScope(sender) {
            val findType = paramCombination.findType()
            val identifier = paramCombination.areaIdentifier()
            val pageIndex = paramCombination.pageIndex()

            val areaManagers = arrayListOf<IAreaManager>()
            if (findType != null) {
                val findResult = IAreaFinder.instance().find(
                    sender,
                    findType,
                    identifier
                ) {
                    areaManagers.add(it)
                }
                if (!findResult.found) {
                    return@localizationScope
                }
            } else {
                areaManagers.addAll(IManagers.instance().merge())
                if (areaManagers.isEmpty()) {
                    message(AreaManagementLanguage.AREA_IS_EMPTY)
                        .send()
                    return@localizationScope
                }
            }

            val chatPage = IChatPages.instance().cache(
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