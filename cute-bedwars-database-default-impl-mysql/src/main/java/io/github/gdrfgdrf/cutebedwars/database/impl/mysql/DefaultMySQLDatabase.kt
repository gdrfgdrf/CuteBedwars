package io.github.gdrfgdrf.cutebedwars.database.impl.mysql

import io.github.gdrfgdrf.cutebedwars.abstracts.core.IPlugin
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.*
import io.github.gdrfgdrf.cutebedwars.database.base.IDatabase
import io.github.gdrfgdrf.cutebedwars.database.base.IService
import io.github.gdrfgdrf.cutebedwars.database.exception.DatabaseException
import io.github.gdrfgdrf.cutebedwars.database.impl.mysql.common.DefaultMySQLDatabaseConfig
import io.github.gdrfgdrf.cutebedwars.database.impl.mysql.common.Mappers
import org.apache.ibatis.logging.LogFactory
import java.io.File
import java.util.concurrent.ConcurrentHashMap

class DefaultMySQLDatabase : IDatabase {
    private var setupLogger = false
    private val services = ConcurrentHashMap<Class<*>, Any>()
    private val serviceClassToImplClass = HashMap<Class<out IService>, Class<*>>()

    override fun getDisplayName(): String {
        return "DefaultDatabase-MySQL"
    }

    override fun load() {
        prepareConfig()
        prepareDataSourceProperties()
        prepareServiceMap()

        if (DefaultMySQLDatabaseConfig.value("EnableDatabaseLogging")) {
            "Use no logging for the default mysql database".logInfo()
            LogFactory.useNoLogging()
        } else {
            "Trying to set up a logger for the default mysql database".logInfo()
            tryImplementation(LogFactory::useSlf4jLogging)
            tryImplementation(LogFactory::useCommonsLogging)
            tryImplementation(LogFactory::useLog4J2Logging)
            tryImplementation(LogFactory::useJdkLogging)
            tryImplementation(LogFactory::useNoLogging)
        }

        MySQLMybatisConfigurer.initialize()

        "$displayName is loaded".logInfo()
    }

    private fun prepareConfig() {
        "Loading the configuration of the default mysql database".logInfo()

        val config = IConfigs.instance().load(
            "default-mysql-database-config.json",
            DefaultMySQLDatabaseConfig::class.java
        )
        DefaultMySQLDatabaseConfig.instance = config
    }

    private fun prepareDataSourceProperties() {
        val fileName = DefaultMySQLDatabaseConfig.value<String>("DataSourcePropertiesFileName")
        val file = File(fileName)
        if (file.exists()) {
            return
        }
        if (!fileName.endsWith("default-default-mysql-datasource.properties")) {
            return
        }

        val resourceInputStream = IPlugin.instance().javaPlugin?.getResource("default-default-mysql-datasource.properties")
            ?: throw NullPointerException("not found default-default-mysql-datasource.properties in jar")
        IFiles.instance().save(resourceInputStream, file)

        "default-default-mysql-datasource.properties is saved".logInfo()
    }

    @Suppress("UNCHECKED_CAST")
    private fun prepareServiceMap() {
        val serviceClasses = HashSet<Class<*>>()
        val serviceImplClasses = HashSet<Class<*>>()

        IClasses.instance().search(
            DefaultMySQLDatabase::class.java.classLoader,
            "io.github.gdrfgdrf.cutebedwars.database.service",
            serviceClasses
        ) {
            return@search it.interfaces.contains(IService::class.java)
        }
        serviceClasses.forEach {
            "Service class: $it".logDebug()
        }

        IClasses.instance().search(
            DefaultMySQLDatabase::class.java.classLoader,
            "io.github.gdrfgdrf.cutebedwars.database.impl.mysql.service.impl",
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
        MySQLMybatisConfigurer.sqlSessionFactory = null
        Mappers.clear()

        "$displayName is closed".logInfo()
    }

    override fun <T : Any?> getService(serviceClass: Class<out IService>): T {
        if (MySQLMybatisConfigurer.sqlSessionFactory == null) {
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