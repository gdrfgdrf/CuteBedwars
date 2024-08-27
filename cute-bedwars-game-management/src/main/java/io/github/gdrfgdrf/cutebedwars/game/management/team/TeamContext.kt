package io.github.gdrfgdrf.cutebedwars.game.management.team

import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game.IGameContext
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.team.ITeamContext
import io.github.gdrfgdrf.cutebedwars.abstracts.notifications.INotifications
import io.github.gdrfgdrf.cutebedwars.beans.annotation.PositiveNumber
import io.github.gdrfgdrf.cutebedwars.beans.pojo.team.Team
import io.github.gdrfgdrf.cutebedwars.game.management.SetterImpl
import io.github.gdrfgdrf.cutebedwars.languages.collect.AreaManagementLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.cutebedwars.utils.StringUtils
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import org.bukkit.command.CommandSender

@ServiceImpl("team_context", needArgument = true)
class TeamContext(
    argumentSet: ArgumentSet,
) : ITeamContext, SetterImpl<Team>() {
    private val gameContext: IGameContext = argumentSet.args[0] as IGameContext
    private val team: Team = argumentSet.args[1] as Team

    init {
        instanceGetter = {
            team
        }
        convert = { clazz, any ->
            team.convert(clazz, any)
        }
    }

    constructor(gameContext: IGameContext, team: Team)
            : this(ArgumentSet(arrayOf(gameContext, team)))

    override fun validate(sender: CommandSender?, withHeader: Boolean): Boolean {
        val area = gameContext.areaContext().manager().area()
        val game = gameContext.game()
        var needDisableGame = false

        val declaredFields = Team::class.java.declaredFields
        declaredFields.forEach {
            if (it.isAnnotationPresent(PositiveNumber::class.java)) {
                it.isAccessible = true
                val number = it.get(team) as Int
                if (number < 0) {
                    needDisableGame = true

                    if (sender == null) {
                        INotifications.get().messageAdministrator {
                            arrayOf(
                                message(AreaManagementLanguage.GAME_VALIDATE_FAILED)
                                    .format(
                                        area.name,
                                        game.name
                                    ),
                                message(AreaManagementLanguage.GAME_TEAM_NEED_POSITIVE_ERROR)
                                    .format(
                                        team.name,
                                        StringUtils.fieldNameToJsonKey(it.name)
                                    )
                            )
                        }
                    } else {
                        localizationScope(sender) {
                            if (withHeader) {
                                message(AreaManagementLanguage.GAME_VALIDATE_FAILED)
                                    .format(
                                        area.name,
                                        game.name
                                    )
                                    .send()
                            }

                            message(AreaManagementLanguage.GAME_TEAM_NEED_POSITIVE_ERROR)
                                .format(
                                    team.name,
                                    StringUtils.fieldNameToJsonKey(it.name)
                                )
                                .send()
                        }
                    }
                }
            }
        }

        if (team.minPlayer > team.maxPlayer) {
            if (sender == null) {
                INotifications.get().messageAdministrator {
                    arrayOf(
                        message(AreaManagementLanguage.GAME_VALIDATE_FAILED)
                            .format(
                                area.name,
                                game.name
                            ),
                        message(AreaManagementLanguage.GAME_TEAM_MIN_PLAYER_BIGGER_THAN_MAX_PLAYER)
                            .format(
                                team.name,
                            )
                    )
                }
            } else {
                localizationScope(sender) {
                    if (withHeader) {
                        message(AreaManagementLanguage.GAME_VALIDATE_FAILED)
                            .format(
                                area.name,
                                game.name
                            )
                            .send()
                    }

                    message(AreaManagementLanguage.GAME_TEAM_MIN_PLAYER_BIGGER_THAN_MAX_PLAYER)
                        .format(
                            team.name,
                        )
                        .send()
                }
            }
        }

        return !needDisableGame
    }

    override fun set(sender: CommandSender, jsonKey: String, any: Any) {
        super.set(sender, jsonKey, any)

        val game = gameContext.game()
        if (team.fixGameMinPlayer(game)) {
            localizationScope(sender) {
                message(AreaManagementLanguage.GAME_MIN_PLAYER_FIXED)
                    .format(game.id, game.minPlayer)
                    .send()
            }
        }
        if (team.fixGameMaxPlayer(game)) {
            localizationScope(sender) {
                message(AreaManagementLanguage.GAME_MAX_PLAYER_FIXED)
                    .format(game.id, game.maxPlayer)
                    .send()
            }
        }
    }
}