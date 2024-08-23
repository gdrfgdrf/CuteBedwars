package io.github.gdrfgdrf.cutebedwars.game.management.game

import io.github.gdrfgdrf.cutebedwars.abstracts.notifications.INotifications
import io.github.gdrfgdrf.cutebedwars.beans.pojo.game.Game
import io.github.gdrfgdrf.cutebedwars.beans.pojo.game.Team
import io.github.gdrfgdrf.cutebedwars.game.management.area.AreaContext
import io.github.gdrfgdrf.cutebedwars.game.management.team.TeamContext
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import org.bukkit.command.CommandSender

class GameContext(
    val areaContext: AreaContext,
    val game: Game
) {
    private val teams = ArrayList<TeamContext>()

    fun addTeam(team: Team) {
        teams.add(TeamContext(this, team))
    }

    fun validate(sender: CommandSender? = null, withHeader: Boolean = false): Boolean {
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
                INotifications.get().messageAdministrator {
                    arrayOf(
                        message(io.github.gdrfgdrf.cutebedwars.languages.collect.AreaManagementLanguage.GAME_VALIDATE_FAILED)
                            .format(
                                area.name,
                                game.name
                            ),
                        message(io.github.gdrfgdrf.cutebedwars.languages.collect.AreaManagementLanguage.GAME_MIN_PLAYER_ERROR)
                            .format()
                    )
                }
            } else {
                localizationScope(sender) {
                    if (withHeader) {
                        message(io.github.gdrfgdrf.cutebedwars.languages.collect.AreaManagementLanguage.GAME_VALIDATE_FAILED)
                            .format(
                                area.name,
                                game.name
                            )
                            .send()
                    }

                    message(io.github.gdrfgdrf.cutebedwars.languages.collect.AreaManagementLanguage.GAME_MIN_PLAYER_ERROR)
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
                        message(io.github.gdrfgdrf.cutebedwars.languages.collect.AreaManagementLanguage.GAME_VALIDATE_FAILED)
                            .format(
                                area.name,
                                game.name
                            ),
                        message(io.github.gdrfgdrf.cutebedwars.languages.collect.AreaManagementLanguage.GAME_MAX_PLAYER_ERROR)
                            .format()
                    )
                }
            } else {
                localizationScope(sender) {
                    if (withHeader) {
                        message(io.github.gdrfgdrf.cutebedwars.languages.collect.AreaManagementLanguage.GAME_VALIDATE_FAILED)
                            .format(
                                area.name,
                                game.name
                            )
                            .send()
                    }

                    message(io.github.gdrfgdrf.cutebedwars.languages.collect.AreaManagementLanguage.GAME_MAX_PLAYER_ERROR)
                        .format()
                        .send()
                }
            }
        }

        if (needDisableGame) {
            game.enable = false
        }

        return !needDisableGame
    }
}