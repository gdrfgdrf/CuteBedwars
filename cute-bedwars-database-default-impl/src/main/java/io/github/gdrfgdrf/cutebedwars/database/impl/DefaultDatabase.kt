package io.github.gdrfgdrf.cutebedwars.database.impl

import cn.pomit.mybatis.configuration.MybatisConfiguration
import io.github.gdrfgdrf.cutebedwars.commons.common.Constants
import io.github.gdrfgdrf.cutebedwars.commons.extension.logInfo
import io.github.gdrfgdrf.cutebedwars.database.base.IDatabase
import io.github.gdrfgdrf.cutebedwars.database.base.IService
import io.github.gdrfgdrf.cutebedwars.database.exception.DatabaseException
import io.github.gdrfgdrf.cutebedwars.database.impl.mapper.PlayerDataMapper
import io.github.gdrfgdrf.cuteframework.bean.BeanManager
import io.github.gdrfgdrf.cuteframework.bean.annotation.Component
import io.github.gdrfgdrf.cuteframework.bean.annotation.Order
import java.io.File

@Order(1)
@Component
class DefaultDatabase : IDatabase {
    override fun getDisplayName(): String {
        return "DefaultDatabase-SQLite"
    }

    override fun load() {
        val file = File(Constants.DEFAULT_DATABASE_FILE_NAME)
        if (!file.exists()) {
            file.createNewFile()
        }

        MybatisConfigurer.initialize()

        "$displayName is loaded".logInfo()
    }

    override fun close() {
        MybatisConfigurer.sqlSessionFactory = null
        MybatisConfigurer.initialize()
        "$displayName is closed".logInfo()
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any?> getService(serviceClass: Class<out IService>): T {
        if (MybatisConfiguration.getSqlSessionFactory() == null) {
            throw DatabaseException("The service could not be gotten because MyBatis has not been loaded")
        }
        return BeanManager.getInstance().getBean(serviceClass.simpleName) as T
    }

    companion object {
        val MAPPERS = arrayOf(
            PlayerDataMapper::class.java
        )
    }

}