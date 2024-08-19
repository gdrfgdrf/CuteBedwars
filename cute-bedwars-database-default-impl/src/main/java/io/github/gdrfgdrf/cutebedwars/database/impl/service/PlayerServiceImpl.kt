package io.github.gdrfgdrf.cutebedwars.database.impl.service

import com.github.dreamyoung.mprelation.ServiceImpl
import io.github.gdrfgdrf.cutebedwars.beans.PlayerData
import io.github.gdrfgdrf.cutebedwars.database.impl.common.Mappers
import io.github.gdrfgdrf.cutebedwars.database.impl.mapper.PlayerDataMapper
import io.github.gdrfgdrf.cuteframework.bean.annotation.Component
import io.github.gdrfgdrf.cuteframework.bean.annotation.Order
import java.util.*

@Order(2)
@Component(name = "IPlayerService")
class PlayerServiceImpl : ServiceImpl<PlayerDataMapper, PlayerData>(), IIPlayerService {
    private var mapper: PlayerDataMapper? = null
        get() {
            if (field == null) {
                field = Mappers.getOrCreateMapper(PlayerDataMapper::class.java)
            }
            return field
        }

    override fun insert(playerData: PlayerData): Int {
        val result = mapper?.insert(playerData) ?: return -1
        return result
    }

    override fun exist(uuid: UUID): Boolean {
        return mapper?.selectByUuid(uuid) != null
    }

    override fun selectByUuid(uuid: UUID): PlayerData? {
        return mapper?.selectByUuid(uuid)
    }
}