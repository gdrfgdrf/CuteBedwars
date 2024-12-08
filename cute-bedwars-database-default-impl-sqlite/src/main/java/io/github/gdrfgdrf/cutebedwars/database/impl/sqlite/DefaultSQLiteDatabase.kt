package io.github.gdrfgdrf.cutebedwars.database.impl.sqlite

import io.github.gdrfgdrf.cutebedwars.abstracts.core.IPlugin
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.*
import io.github.gdrfgdrf.cutebedwars.database.base.IDatabase
import io.github.gdrfgdrf.cutebedwars.database.base.IService
import io.github.gdrfgdrf.cutebedwars.database.exception.DatabaseException
import io.github.gdrfgdrf.cutebedwars.database.impl.sqlite.common.DefaultSQLiteDatabaseConfig
import io.github.gdrfgdrf.cutebedwars.database.impl.sqlite.common.Mappers
import org.apache.ibatis.logging.LogFactory
import java.io.File
import java.util.concurrent.ConcurrentHashMap

class DefaultSQLiteDatabase : IDatabase {
    private var setupLogger = false
    private val services = ConcurrentHashMap<Class<*>, Any>()
    private val serviceClassToImplClass = HashMap<Class<out IService>, Class<*>>()

    override fun getDisplayName(): String {
        return "DefaultDatabase-SQLite"
    }

    override fun load() {
        "The default sqlite database implementation is only applicable when the number of players is small, and it is recommended to replace another database implementation if resources are available".logWarn()

        prepareConfig()
        prepareDataSourceProperties()
        prepareServiceMap()

        val file = File(DefaultSQLiteDatabaseConfig.value<String>("FileName"))
        if (!file.exists()) {
            file.createNewFile()
        }
        if (DefaultSQLiteDatabaseConfig.value("EnableDatabaseLogging")) {
            "Use no logging for the default sqlite database".logInfo()
            LogFactory.useNoLogging()
        } else {
            "Trying to set up a logger for the default sqlite database".logInfo()
            tryImplementation(LogFactory::useSlf4jLogging)
            tryImplementation(LogFactory::useCommonsLogging)
            tryImplementation(LogFactory::useLog4J2Logging)
            tryImplementation(LogFactory::useJdkLogging)
            tryImplementation(LogFactory::useNoLogging)
        }

        SQLiteMybatisConfigurer.initialize()

        "$displayName is loaded".logInfo()
    }

    private fun prepareConfig() {
        "Loading the configuration of the default sqlite database".logInfo()

        val config = IConfigs.instance().load(
            "default-sqlite-database-config.json",
            DefaultSQLiteDatabaseConfig::class.java
        )
        DefaultSQLiteDatabaseConfig.instance = config
    }

    private fun prepareDataSourceProperties() {
        val fileName = DefaultSQLiteDatabaseConfig.value<String>("DataSourcePropertiesFileName")
        val file = File(fileName)
        if (file.exists()) {
            return
        }
        if (!fileName.endsWith("default-sqlite-datasource.properties")) {
            return
        }

        val resourceInputStream = IPlugin.instance().javaPlugin?.getResource("default-sqlite-datasource.properties")
            ?: throw NullPointerException("not found default-sqlite-datasource.properties in jar")
        IFiles.instance().save(resourceInputStream, file)

        "default-sqlite-datasource.properties is saved".logInfo()
    }

    @Suppress("UNCHECKED_CAST")
    private fun prepareServiceMap() {
        val serviceClasses = HashSet<Class<*>>()
        val serviceImplClasses = HashSet<Class<*>>()

        IClasses.instance().search(
            DefaultSQLiteDatabase::class.java.classLoader,
            "io.github.gdrfgdrf.cutebedwars.database.service",
            serviceClasses
        ) {
            return@search it.interfaces.contains(IService::class.java)
        }
        serviceClasses.forEach {
            "Service class: $it".logDebug()
        }

        IClasses.instance().search(
            DefaultSQLiteDatabase::class.java.classLoader,
            "io.github.gdrfgdrf.cutebedwars.database.impl.sqlite.service.impl",
            serviceImplClasses
        ) { implClass ->
            serviceClasses.forEach { serviceClass ->
                if (serviceClass.isAssignableFrom(implClass)) {
                    serviceClassToImplClass[serviceClass as Class<out IService>] = implClass

                    "Service mapping: $serviceClass -> $implClass".logDebug()

                    return@search true
                }
            }
            return@search false
        }
    }

    override fun close() {
        SQLiteMybatisConfigurer.sqlSessionFactory = null
        Mappers.clear()

        "$displayName is closed".logInfo()
    }

    override fun <T : Any?> getService(serviceClass: Class<out IService>): T {
        if (SQLiteMybatisConfigurer.sqlSessionFactory == null) {
            throw DatabaseException("The service could not be gotten because MyBatis has not been loaded")
        }
        return getOrCreateService(serviceClass)
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> getOrCreateService(serviceClass: Class<out IService>): T {
        if (services.containsKey(serviceClass)) {
            return services[serviceClass]!! as T
        }
        if (!serviceClassToImplClass.containsKey(serviceClass)) {
            throw IllegalArgumentException("unknown service type or service impl is not found: $serviceClass")
        }
        val instance = serviceClassToImplClass[serviceClass]!!.getConstructor().newInstance()
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