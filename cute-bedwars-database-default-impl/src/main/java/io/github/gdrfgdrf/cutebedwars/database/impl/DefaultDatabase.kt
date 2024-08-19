package io.github.gdrfgdrf.cutebedwars.database.impl

import io.github.gdrfgdrf.cutebedwars.beans.Config
import io.github.gdrfgdrf.cutebedwars.commons.common.Constants
import io.github.gdrfgdrf.cutebedwars.commons.extension.logInfo
import io.github.gdrfgdrf.cutebedwars.commons.extension.logWarn
import io.github.gdrfgdrf.cutebedwars.database.base.IDatabase
import io.github.gdrfgdrf.cutebedwars.database.base.IService
import io.github.gdrfgdrf.cutebedwars.database.exception.DatabaseException
import io.github.gdrfgdrf.cutebedwars.database.impl.common.Mappers
import io.github.gdrfgdrf.cutebedwars.database.impl.mapper.PlayerDataMapper
import io.github.gdrfgdrf.cuteframework.bean.BeanManager
import io.github.gdrfgdrf.cuteframework.bean.annotation.Component
import io.github.gdrfgdrf.cuteframework.bean.annotation.Order
import org.apache.ibatis.logging.LogFactory
import java.io.File

@Order(1)
@Component
class DefaultDatabase : IDatabase {
    override fun getDisplayName(): String {
        return "DefaultDatabase-SQLite"
    }

    override fun load() {
        "The default database implementation is only applicable when the number of players is small, and it is recommended to replace another database implementation if resources are available".logWarn()

        val file = File(Constants.DEFAULT_DATABASE_FILE_NAME)
        if (!file.exists()) {
            file.createNewFile()
        }
        if (Config.INSTANCE.enableDatabaseLogging == false) {
            LogFactory.useNoLogging()
        }

        MybatisConfigurer.initialize()

        "$displayName is loaded".logInfo()
    }

    override fun close() {
        MybatisConfigurer.sqlSessionFactory = null
        MybatisConfigurer.initialize()

        Mappers.clear()

        "$displayName is closed".logInfo()
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any?> getService(serviceClass: Class<out IService>): T {
        if (MybatisConfigurer.sqlSessionFactory == null) {
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