package io.github.gdrfgdrf.cutebedwars.database.impl.mysql

import com.baomidou.mybatisplus.core.MybatisConfiguration
import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IConstants
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IClasses
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.cutebedwars.database.impl.mysql.common.DefaultMysqlDatabaseConfig
import io.github.gdrfgdrf.cutebedwars.database.impl.mysql.common.database
import org.apache.ibatis.builder.xml.XMLMapperBuilder
import org.apache.ibatis.logging.jdk14.Jdk14LoggingImpl
import org.apache.ibatis.logging.nologging.NoLoggingImpl
import org.apache.ibatis.mapping.Environment
import org.apache.ibatis.session.SqlSessionFactory
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory
import org.springframework.jdbc.datasource.SimpleDriverDataSource
import java.io.File
import java.io.FileInputStream
import java.net.JarURLConnection
import java.sql.Driver
import javax.sql.DataSource

object MysqlMybatisConfigurer {
    var sqlSessionFactory: SqlSessionFactory? = null

    fun initialize() {
        if (sqlSessionFactory == null) {
            sqlSessionFactory = createSqlSessionFactory()
        }
    }

    private fun createSqlSessionFactory(): SqlSessionFactory {
        "Creating the sql session factory (singleton)".logInfo()

        val dataSource = createDataSource()
        val transactionFactory = JdbcTransactionFactory()
        val environment = Environment(database().displayName, transactionFactory, dataSource)
        val configuration = MybatisConfiguration(environment)

        val searchResult = HashSet<Class<*>>()
        IClasses.instance().search(
            MysqlMybatisConfigurer::class.java.classLoader,
            "io.github.gdrfgdrf.cutebedwars.database.impl.mysql.mapper",
            searchResult
        ) { clazz ->
            return@search clazz.superclass == BaseMapper::class.java
        }

        searchResult.forEach {
            "Add a mapper ${it.name}".logInfo()
            configuration.addMapper(it)
        }
        if (DefaultMysqlDatabaseConfig.value("EnableDatabaseLogging")) {
            "Enable database logging (Jdk14LoggingImpl)".logInfo()
            configuration.logImpl = Jdk14LoggingImpl::class.java
        } else {
            "Disable database logging (NoLoggingImpl)".logInfo()
            configuration.logImpl = NoLoggingImpl::class.java
        }
        registryMapperXml(configuration)

        return MybatisSqlSessionFactoryBuilder().build(configuration)

    }

    @Suppress("UNCHECKED_CAST")
    private fun createDataSource(): DataSource {
        "Creating the data source".logInfo()

        val mysqlDriver = Class.forName(DefaultMysqlDatabaseConfig.value("Driver"))

        val dataSource = SimpleDriverDataSource()
        dataSource.setDriverClass(mysqlDriver as Class<Driver>)
        dataSource.url = DefaultMysqlDatabaseConfig.value("Url")

        val username = DefaultMysqlDatabaseConfig.value<String>("DatabaseUsername")
        val password = DefaultMysqlDatabaseConfig.value<String>("DatabasePassword")

        if (!username.isNullOrBlank() && !password.isNullOrBlank()) {
            "The authentication of default database is enabled".logInfo()
            dataSource.username = username
            dataSource.password = password
        } else {
            if (username.isNullOrBlank() && !password.isNullOrBlank()) {
                "The authentication of default database is disabled because the username is blank".logInfo()
            } else {
                if (!username.isNullOrBlank() && password.isNullOrBlank()) {
                    "The authentication of default database is disabled because the password is blank".logInfo()
                }
            }
        }

        return dataSource
    }

    private fun registryMapperXml(configuration: MybatisConfiguration) {
        val classLoader = MysqlMybatisConfigurer::class.java.classLoader
        val mapper = classLoader.getResources("mappers")

        while (mapper.hasMoreElements()) {
            val url = mapper.nextElement()

            if (url.protocol == "file") {
                val path = url.path
                val file = File(path)
                val files = file.listFiles()

                for (targetFile in files!!) {
                    if (!targetFile.name.endsWith("Mapper.xml")) {
                        continue
                    }
                    "Add a mapper xml ${targetFile.name} (1)".logInfo()

                    val inputStream = FileInputStream(targetFile)
                    val xmlMapperBuilder = XMLMapperBuilder(inputStream, configuration, targetFile.path, configuration.sqlFragments)

                    xmlMapperBuilder.parse()
                    inputStream.close()
                }
            } else {
                val urlConnection = url.openConnection() as JarURLConnection
                val jarFile = urlConnection.jarFile
                val entries = jarFile.entries()

                while (entries.hasMoreElements()) {
                    val jarEntry = entries.nextElement()

                    if (jarEntry.name.endsWith("Mapper.xml")) {
                        "Add a mapper xml ${jarEntry.name} (2)".logInfo()

                        val inputStream = jarFile.getInputStream(jarEntry)
                        val xmlMapperBuilder =
                            XMLMapperBuilder(inputStream, configuration, jarEntry.name, configuration.sqlFragments)

                        xmlMapperBuilder.parse()
                        inputStream.close()
                    }
                }
            }
        }
    }



}