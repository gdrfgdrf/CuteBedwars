package io.github.gdrfgdrf.cutebedwars.database.impl.mysql

import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IConfigs
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.cutebedwars.database.base.IDatabase
import io.github.gdrfgdrf.cutebedwars.database.base.IService
import io.github.gdrfgdrf.cutebedwars.database.exception.DatabaseException
import io.github.gdrfgdrf.cutebedwars.database.impl.mysql.common.DefaultMysqlDatabaseConfig
import io.github.gdrfgdrf.cutebedwars.database.impl.mysql.common.Mappers
import org.apache.ibatis.logging.LogFactory
import java.util.concurrent.ConcurrentHashMap

class DefaultMysqlDatabase : IDatabase {
    private var setupLogger = false
    private val services = ConcurrentHashMap<Class<*>, Any>()

    override fun getDisplayName(): String {
        return "DefaultDatabase-Mysql"
    }

    override fun load() {
        prepareConfig()

        if (DefaultMysqlDatabaseConfig.value("EnableDatabaseLogging")) {
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

        MysqlMybatisConfigurer.initialize()

        "$displayName is loaded".logInfo()
    }

    private fun prepareConfig() {
        "Loading the configuration of the default database".logInfo()

        val config = IConfigs.instance().load(
            "default-mysql-database-config.json",
            DefaultMysqlDatabaseConfig::class.java
        )
        DefaultMysqlDatabaseConfig.instance = config
    }

    override fun close() {
        MysqlMybatisConfigurer.sqlSessionFactory = null
        Mappers.clear()

        "$displayName is closed".logInfo()
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any?> getService(serviceClass: Class<out IService>): T {
        if (MysqlMybatisConfigurer.sqlSessionFactory == null) {
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