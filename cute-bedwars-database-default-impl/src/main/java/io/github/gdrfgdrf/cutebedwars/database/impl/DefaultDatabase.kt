package io.github.gdrfgdrf.cutebedwars.database.impl

import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IConfig
import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IConstants
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logWarn
import io.github.gdrfgdrf.cutebedwars.database.base.IDatabase
import io.github.gdrfgdrf.cutebedwars.database.base.IService
import io.github.gdrfgdrf.cutebedwars.database.exception.DatabaseException
import io.github.gdrfgdrf.cutebedwars.database.impl.common.Mappers
import org.apache.ibatis.logging.LogFactory
import java.io.File
import java.util.concurrent.ConcurrentHashMap

class DefaultDatabase : IDatabase {
    private var setupLogger = false
    private val services = ConcurrentHashMap<Class<*>, Any>()

    override fun getDisplayName(): String {
        return "DefaultDatabase-SQLite"
    }

    override fun load() {
        "The default database implementation is only applicable when the number of players is small, and it is recommended to replace another database implementation if resources are available".logWarn()

        val file = File(IConstants.defaultDatabaseFileName())
        if (!file.exists()) {
            file.createNewFile()
        }
        if (IConfig["EnableDatabaseLogging"]) {
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