package io.github.gdrfgdrf.cutebedwars.game.management.area

import io.github.gdrfgdrf.cutebedwars.abstracts.notifications.INotifications
import io.github.gdrfgdrf.cutebedwars.beans.pojo.game.Game
import io.github.gdrfgdrf.cutebedwars.game.management.game.GameContext
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
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
                INotifications.get().messageAdministrator {
                    arrayOf(
                        message(io.github.gdrfgdrf.cutebedwars.languages.collect.AreaManagementLanguage.AREA_VALIDATE_FAILED)
                            .format(manager.area.name),
                    )
                }
            } else {
                localizationScope(sender) {
                    message(io.github.gdrfgdrf.cutebedwars.languages.collect.AreaManagementLanguage.AREA_VALIDATE_FAILED)
                        .format(manager.area.name)
                }
            }
        }
    }
}