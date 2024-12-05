package io.github.gdrfgdrf.cutebedwars.database.impl.sqlite

import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IConstants
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IConfigs
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logWarn
import io.github.gdrfgdrf.cutebedwars.database.base.IDatabase
import io.github.gdrfgdrf.cutebedwars.database.base.IService
import io.github.gdrfgdrf.cutebedwars.database.exception.DatabaseException
import io.github.gdrfgdrf.cutebedwars.database.impl.sqlite.common.DefaultDatabaseConfig
import io.github.gdrfgdrf.cutebedwars.database.impl.sqlite.common.Mappers
import org.apache.ibatis.logging.LogFactory
import java.io.File
import java.util.concurrent.ConcurrentHashMap

class DefaultSqliteDatabase : IDatabase {
    private var setupLogger = false
    private val services = ConcurrentHashMap<Class<*>, Any>()

    override fun getDisplayName(): String {
        return "DefaultDatabase-SQLite"
    }

    override fun load() {
        "The default database implementation is only applicable when the number of players is small, and it is recommended to replace another database implementation if resources are available".logWarn()

        prepareConfig()

        val file = File(IConstants["DEFAULT_DATABASE_FILE_NAME"])
        if (!file.exists()) {
            file.createNewFile()
        }
        if (DefaultDatabaseConfig.value("EnableDatabaseLogging")) {
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

        SqliteMybatisConfigurer.initialize()

        "$displayName is loaded".logInfo()
    }

    private fun prepareConfig() {
        "Loading the configuration of the default database".logInfo()

        val config = IConfigs.instance().load(
            "default-database-config.json",
            DefaultDatabaseConfig::class.java
        )
        DefaultDatabaseConfig.instance = config
    }

    override fun close() {
        SqliteMybatisConfigurer.sqlSessionFactory = null
        Mappers.clear()

        "$displayName is closed".logInfo()
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any?> getService(serviceClass: Class<out IService>): T {
        if (SqliteMybatisConfigurer.sqlSessionFactory == null) {
            throw DatabaseException("The service could not be gotten because MyBatis has not been loaded")
        }
        return getOrCreateService(serviceClass)
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> getOrCreateService(serviceClass: Class<out IService>): T {
        if (services.containsKey(serviceClass)) {
            return services[serviceClass]!! as T
        }
        val instance = serviceClass.getConstructor().newInstance()
        services[serviceClass] = instance

        return instance as T
    }


    private fun tryImplementation(runnable: () -> Unit) {
        runCatching {
            if (setupLogger) {
                return
            }

            runnable()
            setupLogger = true
        }.onFailure {

        }
    }

}