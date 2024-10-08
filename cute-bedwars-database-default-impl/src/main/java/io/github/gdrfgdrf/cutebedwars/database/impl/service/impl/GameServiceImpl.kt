package io.github.gdrfgdrf.cutebedwars.database.impl.service.impl

import io.github.gdrfgdrf.cutebedwars.beans.game.AbstractDatabaseGame
import io.github.gdrfgdrf.cutebedwars.database.impl.beans.game.Game
import io.github.gdrfgdrf.cutebedwars.database.impl.mapper.GameMapper
import io.github.gdrfgdrf.cutebedwars.database.impl.service.IIGameService
import io.github.gdrfgdrf.cutebedwars.database.impl.service.base.BetterServiceImpl
import io.github.gdrfgdrf.cuteframework.bean.annotation.Component
import io.github.gdrfgdrf.cuteframework.bean.annotation.Order

@Order(2)
@Component(name = "IGameService")
class GameServiceImpl : BetterServiceImpl<GameMapper, Game>(), IIGameService {
    override fun insert(game: AbstractDatabaseGame): Int {
        return super.insert(game as Game)
    }
}