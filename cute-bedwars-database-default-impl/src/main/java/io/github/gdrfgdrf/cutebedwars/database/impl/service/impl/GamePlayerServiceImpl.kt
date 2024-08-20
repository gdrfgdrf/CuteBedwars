package io.github.gdrfgdrf.cutebedwars.database.impl.service.impl

import io.github.gdrfgdrf.cutebedwars.beans.game.AbstractGamePlayer
import io.github.gdrfgdrf.cutebedwars.database.impl.beans.game.GamePlayer
import io.github.gdrfgdrf.cutebedwars.database.impl.mapper.GamePlayerMapper
import io.github.gdrfgdrf.cutebedwars.database.impl.service.IIGamePlayerService
import io.github.gdrfgdrf.cutebedwars.database.impl.service.base.BetterServiceImpl
import io.github.gdrfgdrf.cuteframework.bean.annotation.Component
import io.github.gdrfgdrf.cuteframework.bean.annotation.Order

@Order(2)
@Component(name = "IGamePlayerService")
class GamePlayerServiceImpl : BetterServiceImpl<GamePlayerMapper, GamePlayer>(), IIGamePlayerService {
    override fun insert(gamePlayer: AbstractGamePlayer): Int {
        return super.insert(gamePlayer as GamePlayer)
    }
}