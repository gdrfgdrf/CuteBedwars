package io.github.gdrfgdrf.cutebedwars.database.impl.sqlite.service.impl

import io.github.gdrfgdrf.cutebedwars.beans.game.AbstractDatabasePlayer
import io.github.gdrfgdrf.cutebedwars.database.impl.sqlite.beans.game.Player
import io.github.gdrfgdrf.cutebedwars.database.impl.sqlite.mapper.GamePlayerMapper
import io.github.gdrfgdrf.cutebedwars.database.impl.sqlite.service.IIGamePlayerService
import io.github.gdrfgdrf.cutebedwars.database.impl.sqlite.service.base.BetterServiceImpl

class GamePlayerServiceImpl : BetterServiceImpl<GamePlayerMapper, Player>(), IIGamePlayerService {
    override fun insert(gamePlayer: AbstractDatabasePlayer): Int {
        return super.insert(gamePlayer as Player)
    }
}