package io.github.gdrfgdrf.cutebedwars.database.impl.service.impl

import io.github.gdrfgdrf.cutebedwars.beans.AbstractPlayerData
import io.github.gdrfgdrf.cutebedwars.database.impl.beans.PlayerData
import io.github.gdrfgdrf.cutebedwars.database.impl.mapper.PlayerDataMapper
import io.github.gdrfgdrf.cutebedwars.database.impl.service.IIPlayerService
import io.github.gdrfgdrf.cutebedwars.database.impl.service.base.BetterServiceImpl
import io.github.gdrfgdrf.cuteframework.bean.annotation.Component
import io.github.gdrfgdrf.cuteframework.bean.annotation.Order
import java.util.*

@Order(2)
@Component(name = "IPlayerService")
class PlayerServiceImpl : BetterServiceImpl<PlayerDataMapper, PlayerData>(), IIPlayerService {
    override fun insert(playerData: AbstractPlayerData): Int {
        return super.insert(playerData as PlayerData)
    }

    override fun exist(uuid: UUID): Boolean {
        return mapper?.selectByUuid(uuid) != null
    }

    override fun selectByUuid(uuid: UUID): PlayerData? {
        return mapper?.selectByUuid(uuid)
    }
}