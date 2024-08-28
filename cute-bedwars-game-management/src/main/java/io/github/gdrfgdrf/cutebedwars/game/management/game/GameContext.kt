package io.github.gdrfgdrf.cutebedwars.game.management.game

import com.github.yitter.idgen.YitIdHelper
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaContext
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game.IGameContext
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.team.ITeamContext
import io.github.gdrfgdrf.cutebedwars.abstracts.notifications.INotifications
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Status
import io.github.gdrfgdrf.cutebedwars.beans.pojo.game.Game
import io.github.gdrfgdrf.cutebedwars.beans.pojo.team.Team
import io.github.gdrfgdrf.cutebedwars.game.management.SetterImpl
import io.github.gdrfgdrf.cutebedwars.game.management.team.TeamContext
import io.github.gdrfgdrf.cutebedwars.languages.collect.AreaManagementLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import org.bukkit.command.CommandSender

@ServiceImpl("game_context", needArgument = true)
class GameContext(
    argumentSet: ArgumentSet,
) : IGameContext, SetterImpl<Game>() {
    private val areaContext: IAreaContext = argumentSet.args[0] as IAreaContext
    private val game: Game = argumentSet.args[1] as Game
    private val teams = ArrayList<ITeamContext>()

    init {
        instanceGetter = {
            game
        }
        convert = { clazz, any ->
            game.convert(clazz, any)
        }

        if (game.id == null) {
            game.id = YitIdHelper.nextId()
        }
        if (game.name.isNullOrEmpty()) {
            game.name = "temp_name_${game.id}"
        }
    }

    constructor(areaContext: IAreaContext, game: Game)
            : this(ArgumentSet(arrayOf(areaContext, game)))

    override fun areaContext(): IAreaContext = areaContext
    override fun game(): Game = game

    override fun addTeam(team: Team) {
        teams.add(TeamContext(this, team))
    }

    override fun validate(sender: CommandSender?, withHeader: Boolean): Boolean {
        val area = areaContext.manager().area()
        var needDisableGame = false

        teams.forEach { teamContext ->
            if (!teamContext.validate(sender, withHeader)) {
                needDisableGame = true
            }
        }

        var totalMinPlayer = 0
        game.teams.forEach {
            totalMinPlayer += it.minPlayer
        }
        if (game.minPlayer < totalMinPlayer) {
            needDisableGame = true

            if (sender == null) {
                INotifications.get().messageAdministrator {
                    arrayOf(
                        message(AreaManagementLanguage.GAME_VALIDATE_FAILED)
                            .format(
                                area.name,
                                game.name
                            ),
                        message(AreaManagementLanguage.GAME_MIN_PLAYER_ERROR)
                            .format()
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

                    message(AreaManagementLanguage.GAME_MIN_PLAYER_ERROR)
                        .format()
                        .send()
                }
            }
        }

        var totalMaxPlayer = 0
        game.teams.forEach {
            totalMaxPlayer += it.maxPlayer
        }
        if (game.maxPlayer > totalMaxPlayer) {
            needDisableGame = true

            if (sender == null) {
                INotifications.get().messageAdministrator {
                    arrayOf(
                        message(AreaManagementLanguage.GAME_VALIDATE_FAILED)
                            .format(
                                area.name,
                                game.name
                            ),
                        message(AreaManagementLanguage.GAME_MAX_PLAYER_ERROR)
                            .format()
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

                    message(AreaManagementLanguage.GAME_MAX_PLAYER_ERROR)
                        .format()
                        .send()
                }
            }
        }

        if (game.teams.isNullOrEmpty() || game.teams.size == 1) {
            if (sender == null) {
                INotifications.get().messageAdministrator {
                    arrayOf(
                        message(AreaManagementLanguage.GAME_VALIDATE_FAILED)
                            .format(
                                area.name,
                                game.name
                            ),
                        message(AreaManagementLanguage.GAME_TEAM_COUNT_ERROR)
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

                    message(AreaManagementLanguage.GAME_TEAM_COUNT_ERROR)
                        .send()
                }
            }
        }

        if (needDisableGame) {
            game.status = Status.DISABLED
        }

        return !needDisableGame
    }
}