package io.github.gdrfgdrf.cutebedwars.database.impl.mysql.service.impl

import io.github.gdrfgdrf.cutebedwars.beans.game.AbstractDatabasePlayer
import io.github.gdrfgdrf.cutebedwars.database.impl.mysql.mapper.GamePlayerMapper
import io.github.gdrfgdrf.cutebedwars.database.impl.mysql.service.IIGamePlayerService
import io.github.gdrfgdrf.cutebedwars.database.impl.mysql.service.base.BetterServiceImpl
import io.github.gdrfgdrf.cutebedwars.database.impl.sqlite.beans.game.Player

class GamePlayerServiceImpl : BetterServiceImpl<GamePlayerMapper, Player>(), IIGamePlayerService {
    override fun insert(gamePlayer: AbstractDatabasePlayer): Int {
        return super.insert(gamePlayer as Player)
    }
}