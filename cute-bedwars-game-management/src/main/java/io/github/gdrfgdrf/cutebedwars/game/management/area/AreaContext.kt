package io.github.gdrfgdrf.cutebedwars.game.management.area

import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaContext
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaManager
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game.IGameContext
import io.github.gdrfgdrf.cutebedwars.abstracts.notifications.INotifications
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.cutebedwars.beans.pojo.game.Game
import io.github.gdrfgdrf.cutebedwars.game.management.game.GameContext
import io.github.gdrfgdrf.cutebedwars.languages.collect.AreaManagementLanguage
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.localizationScope
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import org.bukkit.command.CommandSender

@ServiceImpl("area_context", needArgument = true)
class AreaContext(
    argumentSet: ArgumentSet,
) : IAreaContext {
    override val manager = argumentSet.args[0] as IAreaManager
    override val games = ArrayList<IGameContext>()

    init {
        manager.area.games.forEach {
            addGame(it, false)
        }
    }

    constructor(areaManager: IAreaManager)
            : this(ArgumentSet(arrayOf(areaManager)))

    override fun createGame(name: String): IGameContext {
        "Creating a game under an area, game's name: $name, area's name: ${manager.area.name}".logInfo()

        val game = Game()
        game.areaId = this.manager.area.id
        game.name = name

        return GameContext(this, game)
    }

    override fun addGame(game: Game, addToBean: Boolean) {
        "Adding a game to an area, game's name: ${game.name}, area's name: ${manager.area.name}".logInfo()

        games.add(GameContext(this, game))
        if (addToBean) {
            manager.area.games.add(game)
        }
    }

    override fun addGame(gameContext: IGameContext, addToBean: Boolean) {
        "Adding a game to an area, game's name: ${gameContext.game.name}, area's name: ${manager.area.name}".logInfo()

        games.add(gameContext)
        if (addToBean) {
            manager.area.games.add(gameContext.game)
        }
    }

    override fun getGame(id: Long): IGameContext? {
        return games.stream()
            .filter {
                it.game.id == id
            }
            .findAny()
            .orElse(null)
    }

    override fun getGame(name: String): List<IGameContext> {
        return games.stream()
            .filter {
                it.game.name == name
            }
            .toList()
    }

    override fun validate(sender: CommandSender?) {
        var success = true

        games.forEach {
            if (!it.validate(sender, true)) {
                success = false
            }
        }

        if (!success) {
            if (sender == null) {
                INotifications.instance().messageAdministrator {
                    arrayOf(
                        message(AreaManagementLanguage.AREA_VALIDATE_FAILED)
                            .format0(manager.area.name),
                    )
                }
            } else {
                localizationScope(sender) {
                    message(AreaManagementLanguage.AREA_VALIDATE_FAILED)
                        .format0(manager.area.name)
                }
            }
        }
    }
}