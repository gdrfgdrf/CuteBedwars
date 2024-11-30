package io.github.gdrfgdrf.cutebedwars.game.management.area

import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaContext
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaManager
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game.IGameContext
import io.github.gdrfgdrf.cutebedwars.abstracts.notifications.INotifications
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.cutebedwars.beans.Convertible
import io.github.gdrfgdrf.cutebedwars.beans.pojo.area.Area
import io.github.gdrfgdrf.cutebedwars.beans.pojo.game.Game
import io.github.gdrfgdrf.cutebedwars.game.management.SetterImpl
import io.github.gdrfgdrf.cutebedwars.game.management.game.GameContext
import io.github.gdrfgdrf.cutebedwars.languages.collect.AreaManagementLanguage
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.localizationScope
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import org.bukkit.command.CommandSender

@ServiceImpl("area_context", needArgument = true)
class AreaContext(
    argumentSet: ArgumentSet,
) : IAreaContext, SetterImpl<Area>() {
    override val manager = argumentSet.args[0] as IAreaManager
    override val games = ArrayList<IGameContext>()

    init {
        instanceGetter = {
            manager.area
        }
        convert = { clazz, any ->
            Convertible.of(Area::class.java).invoke(clazz, any)
        }
    }

    constructor(areaManager: IAreaManager)
            : this(ArgumentSet(arrayOf(areaManager)))

    override fun initialize() {
        manager.area.games.forEach {
            addGame(it, false)
        }
    }

    override fun createGame(name: String): IGameContext {
        "Creating a game under an area, game's name: $name, area's name: ${manager.area.name}".logInfo()

        val game = Game()
        game.areaId = this.manager.area.id
        game.name = name

//        val fakeCoordinate1 = Coordinate()
//        fakeCoordinate1.x = 5.0
//        fakeCoordinate1.y = 4.0
//        fakeCoordinate1.z = 6.0
//
//        val fakeCoordinate2 = Coordinate()
//        fakeCoordinate2.x = 10.0
//        fakeCoordinate2.y = 13.0
//        fakeCoordinate2.z = 11.0
//
//        val fakeRegion = Region()
//        fakeRegion.firstCoordinate = fakeCoordinate1
//        fakeRegion.secondCoordinate = fakeCoordinate2
//
//        game.region = fakeRegion

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