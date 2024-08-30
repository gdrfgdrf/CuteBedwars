package io.github.gdrfgdrf.cutebedwars.commands

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaManager
import io.github.gdrfgdrf.cutebedwars.commands.base.SubCommand
import io.github.gdrfgdrf.cutebedwars.commands.finder.BetterAreaFinder
import io.github.gdrfgdrf.cutebedwars.languages.collect.AreaManagementLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandSyntaxLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.command.CommandSender

object ModifyArea : SubCommand(
    command = ICommands.valueOf("MODIFY_AREA")
) {
    override fun syntax(): LanguageString? = null
    override fun description(): LanguageString? = null

//    override fun syntax(): LanguageString? = CommandSyntaxLanguage.MODIFY_AREA
//    override fun description(): LanguageString? = CommandDescriptionLanguage.MODIFY_AREA

    override fun run(sender: CommandSender, args: Array<String>, pageSchemeIndex: Int) {
        localizationScope(sender) {
            val findType = args[0]
            val identifier = args[1]
            val areaProperty = args[2]
            val value = args[3]

            val areaManager: IAreaManager = BetterAreaFinder.find(sender, findType, identifier) ?: return@localizationScope

            runCatching {
                areaManager.context().set(sender, areaProperty, value)

                message(AreaManagementLanguage.SET_PROPERTY_SUCCESS)
                    .format(areaProperty, value)
                    .send()
            }.onFailure {
                if (it is NoSuchFieldException) {
                    message(AreaManagementLanguage.NOT_FOUND_PROPERTY)
                        .format(areaProperty)
                        .send()
                    return@localizationScope
                }
                if (it is SecurityException) {
                    message(AreaManagementLanguage.PROPERTY_CANNOT_ACCESS)
                        .format(areaProperty)
                        .send()
                    return@localizationScope
                }
                if (it::class.java.name == "io.github.gdrfgdrf.cutebedwars.game.management.exception.UndefinablePropertyException") {
                    message(AreaManagementLanguage.PROPERTY_IS_UNDEFINABLE)
                        .format(areaProperty)
                        .send()
                    return@localizationScope
                }
                if (it::class.java.name == "io.github.gdrfgdrf.cutebedwars.game.management.exception.NotPositiveNumberException") {
                    message(AreaManagementLanguage.NOT_POSITIVE_NUMBER)
                        .format(areaProperty)
                        .send()
                }
                if (it::class.java.name == "io.github.gdrfgdrf.cutebedwars.game.management.exception.ConvertException") {
                    message(AreaManagementLanguage.CONVERT_ERROR)
                        .send()
                }
            }
        }

    }
}