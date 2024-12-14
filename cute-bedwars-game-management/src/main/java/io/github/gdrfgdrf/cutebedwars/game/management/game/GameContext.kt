package io.github.gdrfgdrf.cutebedwars.game.management.game

import com.github.yitter.idgen.YitIdHelper
import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IConstants
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaContext
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game.IGameContext
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.team.ITeamContext
import io.github.gdrfgdrf.cutebedwars.abstracts.notifications.INotifications
import io.github.gdrfgdrf.cutebedwars.abstracts.storage.AbstractGameCommitStorage
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.cutebedwars.beans.Convertible
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Status
import io.github.gdrfgdrf.cutebedwars.beans.pojo.game.Game
import io.github.gdrfgdrf.cutebedwars.beans.pojo.team.Team
import io.github.gdrfgdrf.cutebedwars.game.management.SetterImpl
import io.github.gdrfgdrf.cutebedwars.game.management.team.TeamContext
import io.github.gdrfgdrf.cutebedwars.languages.collect.AreaManagementLanguage
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.localizationScope
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import org.bukkit.command.CommandSender

@ServiceImpl("game_context", needArgument = true)
class GameContext(
    argumentSet: ArgumentSet,
) : IGameContext, SetterImpl<Game>() {
    override val areaContext: IAreaContext = argumentSet.args[0] as IAreaContext
    override val game = argumentSet.args[1] as Game
    override val teams = ArrayList<ITeamContext>()

    override val commitStorage: AbstractGameCommitStorage

    init {
        instanceGetter = {
            game
        }
        convert = { clazz, any ->
            Convertible.of(Game::class.java).invoke(clazz, any)
        }

        if (game.id == null) {
            val id = YitIdHelper.nextId()
            "Set an id: $id to a game (area's id: ${game.areaId})".logInfo()
            game.id = id
        }
        if (game.name.isNullOrEmpty()) {
            "The game (area's id: ${game.areaId}, id: ${game.id})'s name is null, setting to \"temp_name_${game.id}\""
            game.name = "temp_name_${game.id}"
        }

        "Creating the commit storage for a game id: ${game.id}, name: ${game.name}, area's id: ${game.areaId}".logInfo()
        commitStorage = AbstractGameCommitStorage.new("${IConstants["AREA_FOLDER"]}/${game.areaId}/commits/games/${game.id}")
    }

    constructor(areaContext: IAreaContext, game: Game)
            : this(ArgumentSet(arrayOf(areaContext, game)))

    override fun addTeam(team: Team) {
        "Adding a team to a game id: ${game.id}, name: ${game.name}, area's id: ${game.areaId}".logInfo()
        teams.add(TeamContext(this, team))
    }

    override fun validate(sender: CommandSender?, withHeader: Boolean): Boolean {
        "Validating a game id: ${game.id}, name: ${game.name}, area's id: ${game.areaId}".logInfo()

        val area = areaContext.manager.area
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
                INotifications.instance().messageAdministrator {
                    arrayOf(
                        message(AreaManagementLanguage.GAME_VALIDATE_FAILED)
                            .format0(
                                area.name,
                                game.name
                            ),
                        message(AreaManagementLanguage.GAME_MIN_PLAYER_ERROR)
                            .format0()
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

                    message(AreaManagementLanguage.GAME_MIN_PLAYER_ERROR)
                        .format0()
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
                INotifications.instance().messageAdministrator {
                    arrayOf(
                        message(AreaManagementLanguage.GAME_VALIDATE_FAILED)
                            .format0(
                                area.name,
                                game.name
                            ),
                        message(AreaManagementLanguage.GAME_MAX_PLAYER_ERROR)
                            .format0()
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

                    message(AreaManagementLanguage.GAME_MAX_PLAYER_ERROR)
                        .format0()
                        .send()
                }
            }
        }

        if (game.teams.isNullOrEmpty() || game.teams.size == 1) {
            if (sender == null) {
                INotifications.instance().messageAdministrator {
                    arrayOf(
                        message(AreaManagementLanguage.GAME_VALIDATE_FAILED)
                            .format0(
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
                            .format0(
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