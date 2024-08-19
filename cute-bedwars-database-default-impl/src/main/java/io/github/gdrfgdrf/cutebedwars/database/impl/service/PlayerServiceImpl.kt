package io.github.gdrfgdrf.cutebedwars.database.impl.service

import cn.pomit.mybatis.ProxyHandlerFactory
import cn.pomit.mybatis.configuration.MybatisConfiguration
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.core.toolkit.IdWorker
import io.github.gdrfgdrf.cutebedwars.beans.PlayerData
import io.github.gdrfgdrf.cutebedwars.database.impl.mapper.PlayerDataMapper
import io.github.gdrfgdrf.cutebedwars.database.service.IPlayerService
import io.github.gdrfgdrf.cuteframework.bean.annotation.Component
import io.github.gdrfgdrf.cuteframework.bean.annotation.Order
import java.util.*

@Order(2)
@Component(name = "IPlayerService")
class PlayerServiceImpl : IPlayerService {
    private var mapper: PlayerDataMapper? = null
        get() {
            if (field == null) {
                field = ProxyHandlerFactory.getMapper(PlayerDataMapper::class.java)
            }
            createTable()

            return field
        }
    private fun createTable() {
        val session = MybatisConfiguration.getSqlSessionFactory().openSession(true)
        val statement = session.connection.createStatement()
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT(0) NOT NULL," +
                "uuid VARCHAR(255) NOT NULL," +
                "PRIMARY KEY (id)" +
                ")")

        session.commit(false)

        statement.close()
        session.close()
    }

    override fun insert(playerData: PlayerData): Int {
        playerData.id = IdWorker.getId(playerData)

        val result = mapper?.insert(playerData) ?: return -1
        return result
    }

    override fun exist(uuid: UUID): Boolean {
        return mapper?.selectByUuid(uuid.toString()) != null
    }

    override fun selectByUuid(uuid: UUID): PlayerData? {
        return mapper?.selectByUuid(uuid.toString())
    }
}