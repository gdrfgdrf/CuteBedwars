package io.github.gdrfgdrf.cutebedwars.commands.sub

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IRequestTypes
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.IManagers
import io.github.gdrfgdrf.cutebedwars.abstracts.requests.IRequests
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.AbstractSubCommand
import io.github.gdrfgdrf.cutebedwars.languages.collect.AreaManagementLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandSyntaxLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.command.CommandSender
import java.util.concurrent.TimeUnit

object CreateArea : AbstractSubCommand(
    command = ICommands.valueOf("CREATE_AREA")
){
    override fun syntax(): LanguageString? = CommandSyntaxLanguage.CREATE_AREA
    override fun description(): LanguageString? = CommandDescriptionLanguage.CREATE_AREA

    override fun run(sender: CommandSender, args: Array<String>, paramSchemeIndex: Int) {
        localizationScope(sender) {
            val areaName = args[0]
            val managers = IManagers.instance()
            val requests = IRequests.instance()

            val sameNameAreas = managers.get(areaName)
            if (sameNameAreas != null && sameNameAreas.size >= 1) {
                val pair = requests.auto(type = IRequestTypes.valueOf("CREATE_AREA"), sender = sender)
                val new = pair.first
                val request = pair.second

                if (new) {
                    message(AreaManagementLanguage.DUPLICATE_AREA_NAME_WARNING)
                        .format(areaName, TimeUnit.SECONDS.convert(request.timeout(), request.timeUnit()))
                        .send()
                    return@localizationScope
                }
            }
            requests.removeForAuto(type = IRequestTypes.valueOf("CREATE_AREA"), sender = sender)

            message(AreaManagementLanguage.CREATING_AREA)
                .format(areaName)
                .send()

            val areaManager = managers.createArea(areaName)
            if (managers.get(areaManager.area().id) != null) {
                message(AreaManagementLanguage.DUPLICATE_AREA_ID_ERROR)
                    .format(areaManager.area().id)
                    .send()
                return@localizationScope
            }

            managers.register(areaManager)

            message(AreaManagementLanguage.CREATE_AREA_FINISHED)
                .format(areaName, areaManager.area().id)
                .send()
        }
    }
}