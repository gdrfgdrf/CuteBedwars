package io.github.gdrfgdrf.cutebedwars.commands.sub

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IRequestTypes
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaManager
import io.github.gdrfgdrf.cutebedwars.abstracts.requests.IRequests
import io.github.gdrfgdrf.cutebedwars.commands.finder.BetterAreaFinder
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.AbstractSubCommand
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.IParamCombination
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILanguageString
import io.github.gdrfgdrf.cutebedwars.languages.collect.AreaManagementLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandSyntaxLanguage
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.localizationScope
import org.bukkit.command.CommandSender
import java.util.concurrent.TimeUnit

object CreateGame : AbstractSubCommand(
    command = ICommands.valueOf("CREATE_GAME")
) {
    override fun syntax(): ILanguageString? = CommandSyntaxLanguage.CREATE_GAME
    override fun description(): ILanguageString? = CommandDescriptionLanguage.CREATE_GAME

    override fun run(sender: CommandSender, args: Array<String>, paramCombination: IParamCombination) {
        localizationScope(sender) {
            val findType = paramCombination.findType()
            val areaIdentifier = paramCombination.areaIdentifier()
            val gameName = paramCombination.notNullString("GAME_NAME")

            val areaManager: IAreaManager = BetterAreaFinder.find(
                sender,
                findType!!,
                areaIdentifier
            ) ?: return@localizationScope
            val areaContext = areaManager.context()
            val areaName = areaManager.area().name

            val requests = IRequests.instance()

            val sameNameGames = areaContext.getGame(gameName)
            if (sameNameGames.isNotEmpty()) {
                val pair = requests.auto(type = IRequestTypes.valueOf("CREATE_GAME"), sender = sender)
                val new = pair.first
                val request = pair.second

                if (new) {
                    message(AreaManagementLanguage.DUPLICATE_GAME_NAME_WARNING)
                        .format0(
                            areaName,
                            gameName,
                            TimeUnit.SECONDS.convert(request.timeout(), request.timeUnit())
                        )
                        .send()
                    return@localizationScope
                }
            }
            requests.removeForAuto(type = IRequestTypes.valueOf("CREATE_GAME"), sender = sender)

            message(AreaManagementLanguage.CREATING_GAME)
                .format0(areaName, gameName)
                .send()

            val gameContext = areaContext.createGame(gameName)
            if (areaContext.getGame(gameContext.game().id) != null) {
                message(AreaManagementLanguage.DUPLICATE_GAME_ID_ERROR)
                    .format0(gameContext.game().id)
                    .send()
                return@localizationScope
            }

            areaContext.addGame(gameContext)

            message(AreaManagementLanguage.CREATE_GAME_FINISHED)
                .format0(areaName, gameContext.game().name)
                .send()
        }
    }
}