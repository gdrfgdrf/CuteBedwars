package io.github.gdrfgdrf.cutebedwars.database.impl

import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IConfig
import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IConstants
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logWarn
import io.github.gdrfgdrf.cutebedwars.database.base.IDatabase
import io.github.gdrfgdrf.cutebedwars.database.base.IService
import io.github.gdrfgdrf.cutebedwars.database.exception.DatabaseException
import io.github.gdrfgdrf.cutebedwars.database.impl.common.Mappers
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

        val file = File(IConstants.defaultDatabaseFileName())
        if (!file.exists()) {
            file.createNewFile()
        }
        if (IConfig.enableDatabaseLogging() == false) {
            "Use no logging for the default database".logInfo()
            LogFactory.useNoLogging()
        } else {
            "Trying to set up a logger for the default database".logInfo()
            tryImplementation(LogFactory::useSlf4jLogging)
            tryImplementation(LogFactory::useCommonsLogging)
            tryImplementation(LogFactory::useLog4J2Logging)
            tryImplementation(LogFactory::useJdkLogging)
            tryImplementation(LogFactory::useNoLogging)
        }

        MybatisConfigurer.initialize()

        "$displayName is loaded".logInfo()
    }

    override fun close() {
        MybatisConfigurer.sqlSessionFactory = null
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

    private fun tryImplementation(runnable: () -> Unit) {
        runCatching {
            runnable()
        }.onFailure {

        }
    }

}