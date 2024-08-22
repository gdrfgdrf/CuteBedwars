package io.github.gdrfgdrf.cutebedwars.game.managers.area

import io.github.gdrfgdrf.cutebedwars.beans.pojo.game.Game
import io.github.gdrfgdrf.cutebedwars.game.managers.game.GameContext
import io.github.gdrfgdrf.cutebedwars.locale.collect.AreaManagementLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.cutebedwars.notification.Notifications
import org.bukkit.command.CommandSender

class AreaContext(
    val manager: AreaManager
) {
    private val games = ArrayList<GameContext>()

    fun addGame(game: Game) {
        games.add(GameContext(this, game))
    }

    fun validate(sender: CommandSender? = null) {
        var success = true

        games.forEach {
            if (!it.validate(sender, true)) {
                success = false
            }
        }

        if (!success) {
            if (sender == null) {
                Notifications.messageAdministrator {
                    arrayOf(
                        message(AreaManagementLanguage.AREA_VALIDATE_FAILED)
                            .format(manager.area.name),
                    )
                }
            } else {
                localizationScope(sender) {
                    message(AreaManagementLanguage.AREA_VALIDATE_FAILED)
                        .format(manager.area.name)
                }
            }
        }
    }
}