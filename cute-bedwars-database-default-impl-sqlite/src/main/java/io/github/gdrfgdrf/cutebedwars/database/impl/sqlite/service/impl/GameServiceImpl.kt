package io.github.gdrfgdrf.cutebedwars.database.impl.sqlite.service.impl

import io.github.gdrfgdrf.cutebedwars.beans.game.AbstractDatabaseGame
import io.github.gdrfgdrf.cutebedwars.database.impl.sqlite.beans.game.Game
import io.github.gdrfgdrf.cutebedwars.database.impl.sqlite.mapper.GameMapper
import io.github.gdrfgdrf.cutebedwars.database.impl.sqlite.service.IIGameService
import io.github.gdrfgdrf.cutebedwars.database.impl.sqlite.service.base.BetterServiceImpl

class GameServiceImpl : BetterServiceImpl<GameMapper, Game>(), IIGameService {
    override fun insert(game: AbstractDatabaseGame): Int {
        return super.insert(game as Game)
    }
}