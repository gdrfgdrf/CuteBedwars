package io.github.gdrfgdrf.cutebedwars.game.management.area

import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaContext
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaManager
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game.IGameContext
import io.github.gdrfgdrf.cutebedwars.abstracts.notifications.INotifications
import io.github.gdrfgdrf.cutebedwars.beans.pojo.area.Area
import io.github.gdrfgdrf.cutebedwars.beans.pojo.game.Game
import io.github.gdrfgdrf.cutebedwars.game.management.SetterImpl
import io.github.gdrfgdrf.cutebedwars.game.management.game.GameContext
import io.github.gdrfgdrf.cutebedwars.languages.collect.AreaManagementLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import org.bukkit.command.CommandSender

@ServiceImpl("area_context", needArgument = true)
class AreaContext(
    argumentSet: ArgumentSet,
) : IAreaContext, SetterImpl<Area>() {
    private val manager: IAreaManager = argumentSet.args[0] as IAreaManager
    private val games = ArrayList<IGameContext>()

    init {
        instanceGetter = {
            manager.area()
        }
        convert = { clazz, any ->
            manager.area().convert(clazz, any)
        }
    }

    constructor(areaManager: IAreaManager)
            : this(ArgumentSet(arrayOf(areaManager)))

    override fun manager(): IAreaManager = manager

    override fun createGame(name: String): IGameContext {
        val game = Game()
        game.areaId = this.manager.area().id
        return GameContext(this, game)
    }

    override fun addGame(game: Game) {
        games.add(GameContext(this, game))
    }

    override fun addGame(gameContext: IGameContext) {
        games.add(gameContext)
    }

    override fun getGame(id: Long): IGameContext? {
        return games.stream()
            .filter {
                it.game().id == id
            }
            .findAny()
            .orElse(null)
    }

    override fun getGame(name: String): List<IGameContext> {
        return games.stream()
            .filter {
                it.game().name == name
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
                INotifications.get().messageAdministrator {
                    arrayOf(
                        message(AreaManagementLanguage.AREA_VALIDATE_FAILED)
                            .format(manager.area().name),
                    )
                }
            } else {
                localizationScope(sender) {
                    message(AreaManagementLanguage.AREA_VALIDATE_FAILED)
                        .format(manager.area().name)
                }
            }
        }
    }
}