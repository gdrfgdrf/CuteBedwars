package io.github.gdrfgdrf.cutebedwars.database.impl.mysql.service.impl

import io.github.gdrfgdrf.cutebedwars.beans.game.AbstractDatabaseGame
import io.github.gdrfgdrf.cutebedwars.database.impl.mysql.mapper.GameMapper
import io.github.gdrfgdrf.cutebedwars.database.impl.mysql.service.IIGameService
import io.github.gdrfgdrf.cutebedwars.database.impl.mysql.service.base.BetterServiceImpl
import io.github.gdrfgdrf.cutebedwars.database.impl.sqlite.beans.game.Game

class GameServiceImpl : BetterServiceImpl<GameMapper, Game>(), IIGameService {
    override fun insert(game: AbstractDatabaseGame): Int {
        return super.insert(game as Game)
    }
}