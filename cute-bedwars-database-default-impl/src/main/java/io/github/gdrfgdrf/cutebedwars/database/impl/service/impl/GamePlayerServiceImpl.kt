package io.github.gdrfgdrf.cutebedwars.database.impl.service.impl

import io.github.gdrfgdrf.cutebedwars.beans.game.AbstractDatabasePlayer
import io.github.gdrfgdrf.cutebedwars.database.impl.beans.game.Player
import io.github.gdrfgdrf.cutebedwars.database.impl.mapper.GamePlayerMapper
import io.github.gdrfgdrf.cutebedwars.database.impl.service.IIGamePlayerService
import io.github.gdrfgdrf.cutebedwars.database.impl.service.base.BetterServiceImpl
import io.github.gdrfgdrf.cuteframework.bean.annotation.Component
import io.github.gdrfgdrf.cuteframework.bean.annotation.Order

@Order(2)
@Component(name = "IGamePlayerService")
class GamePlayerServiceImpl : BetterServiceImpl<GamePlayerMapper, Player>(), IIGamePlayerService {
    override fun insert(gamePlayer: AbstractDatabasePlayer): Int {
        return super.insert(gamePlayer as Player)
    }
}