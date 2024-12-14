package io.github.gdrfgdrf.cutebedwars.game.management.team

import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game.IGameContext
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.team.ITeamContext
import io.github.gdrfgdrf.cutebedwars.abstracts.notifications.INotifications
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IConvertors
import io.github.gdrfgdrf.cutebedwars.beans.annotation.PositiveNumber
import io.github.gdrfgdrf.cutebedwars.beans.pojo.team.Team
import io.github.gdrfgdrf.cutebedwars.languages.collect.AreaManagementLanguage
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.localizationScope
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import org.bukkit.command.CommandSender

@ServiceImpl("team_context", needArgument = true)
class TeamContext(
    argumentSet: ArgumentSet,
) : ITeamContext {
    private val gameContext: IGameContext = argumentSet.args[0] as IGameContext
    private val team = argumentSet.args[1] as Team

    constructor(gameContext: IGameContext, team: Team)
            : this(ArgumentSet(arrayOf(gameContext, team)))

    override fun validate(sender: CommandSender?, withHeader: Boolean): Boolean {
        "Validating a team id: ${team.id}, name: ${team.name}, game's id: ${team.gameId}"

        val area = gameContext.areaContext.manager.area
        val game = gameContext.game
        var needDisableGame = false

        val declaredFields = Team::class.java.declaredFields
        declaredFields.forEach {
            if (it.isAnnotationPresent(PositiveNumber::class.java)) {
                it.isAccessible = true
                val number = it.get(team) as Int
                if (number < 0) {
                    needDisableGame = true

                    if (sender == null) {
                        INotifications.instance().messageAdministrator {
                            arrayOf(
                                message(AreaManagementLanguage.GAME_VALIDATE_FAILED)
                                    .format0(
                                        area.name,
                                        game.name
                                    ),
                                message(AreaManagementLanguage.GAME_TEAM_NEED_POSITIVE_ERROR)
                                    .format0(
                                        team.name,
                                        IConvertors.instance().fieldNameToJsonKey(it.name)
                                    )
                            )
                        }
                    } else {
                        localizationScope(sender) {
                            if (withHeader) {
                                message(AreaManagementLanguage.GAME_VALIDATE_FAILED)
                                    .format0(
                                        area.name,
                                        game.name
                                    )
                                    .send()
                            }

                            message(AreaManagementLanguage.GAME_TEAM_NEED_POSITIVE_ERROR)
                                .format0(
                                    team.name,
                                    IConvertors.instance().fieldNameToJsonKey(it.name)
                                )
                                .send()
                        }
                    }
                }
            }
        }

        if (team.minPlayer > team.maxPlayer) {
            if (sender == null) {
                INotifications.instance().messageAdministrator {
                    arrayOf(
                        message(AreaManagementLanguage.GAME_VALIDATE_FAILED)
                            .format0(
                                area.name,
                                game.name
                            ),
                        message(AreaManagementLanguage.GAME_TEAM_MIN_PLAYER_BIGGER_THAN_MAX_PLAYER)
                            .format0(
                                team.name,
                            )
                    )
                }
            } else {
                localizationScope(sender) {
                    if (withHeader) {
                        message(AreaManagementLanguage.GAME_VALIDATE_FAILED)
                            .format0(
                                area.name,
                                game.name
                            )
                            .send()
                    }

                    message(AreaManagementLanguage.GAME_TEAM_MIN_PLAYER_BIGGER_THAN_MAX_PLAYER)
                        .format0(
                            team.name,
                        )
                        .send()
                }
            }
        }

        return !needDisableGame
    }
}