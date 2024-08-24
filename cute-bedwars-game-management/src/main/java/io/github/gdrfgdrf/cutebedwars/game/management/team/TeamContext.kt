package io.github.gdrfgdrf.cutebedwars.game.management.team

import io.github.gdrfgdrf.cutebedwars.abstracts.notifications.INotifications
import io.github.gdrfgdrf.cutebedwars.beans.annotation.PositiveNumber
import io.github.gdrfgdrf.cutebedwars.beans.annotation.Undefinable
import io.github.gdrfgdrf.cutebedwars.beans.pojo.team.Team
import io.github.gdrfgdrf.cutebedwars.game.management.exception.UndefinablePropertyException
import io.github.gdrfgdrf.cutebedwars.game.management.game.GameContext
import io.github.gdrfgdrf.cutebedwars.languages.collect.AreaManagementLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.cutebedwars.utils.StringUtils
import org.bukkit.command.CommandSender

class TeamContext(
    val gameContext: GameContext,
    val team: Team,
) {
    fun validate(sender: CommandSender? = null, withHeader: Boolean = false): Boolean {
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

    fun set(sender: CommandSender, jsonKey: String, any: Any) {
        val fieldName = StringUtils.jsonKeyToFieldName(jsonKey)
        val declaredField = Team::class.java.getDeclaredField(fieldName)

        var undefinable = false
        var positiveNumber = false
        if (declaredField.isAnnotationPresent(Undefinable::class.java)) {
            undefinable = true
        }
        if (declaredField.isAnnotationPresent(PositiveNumber::class.java)) {
            positiveNumber = true
        }
        if (undefinable) {
            throw UndefinablePropertyException()
        }

        if (positiveNumber) {
            runCatching {
                val number = team.convert(java.lang.Integer::class.java, any) as Int
                if (number < 0) {
                    throw IllegalArgumentException()
                }
            }.onFailure {
                throw IllegalArgumentException()
            }
        }

        val converted = team.convert(declaredField.type, any)

        declaredField.isAccessible = true
        declaredField.set(team, converted)

        val game = gameContext.game
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