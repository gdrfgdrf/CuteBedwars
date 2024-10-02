package io.github.gdrfgdrf.cutebedwars.database.impl

import com.baomidou.mybatisplus.core.MybatisConfiguration
import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IConfig
import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IConstants
import io.github.gdrfgdrf.cutebedwars.database.impl.common.database
import io.github.gdrfgdrf.cutebedwars.utils.extension.logInfo
import io.github.gdrfgdrf.cuteframework.utils.ClassUtils
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

object MybatisConfigurer {
    var sqlSessionFactory: SqlSessionFactory? = null

    fun initialize() {
        if (sqlSessionFactory == null) {
            sqlSessionFactory = createSqlSessionFactory()
        }
    }

    private fun createSqlSessionFactory(): SqlSessionFactory {
        val dataSource = createDataSource()
        val transactionFactory = JdbcTransactionFactory()
        val environment = Environment(database().displayName, transactionFactory, dataSource)
        val configuration = MybatisConfiguration(environment)

        val searchResult = HashSet<Class<*>>()

        ClassUtils.searchJar(
            MybatisConfigurer::class.java.classLoader,
            "io.github.gdrfgdrf.cutebedwars.database.impl.mapper",
            { clazz ->
                return@searchJar clazz.superclass == BaseMapper::class.java
            },
            searchResult
        )

        searchResult.forEach {
            "Add a mapper ${it.name}".logInfo()
            configuration.addMapper(it)
        }
        if (IConfig.enableDatabaseLogging() == true) {
            configuration.logImpl = Jdk14LoggingImpl::class.java
        } else {
            configuration.logImpl = NoLoggingImpl::class.java
        }
        registryMapperXml(configuration)

        return MybatisSqlSessionFactoryBuilder().build(configuration)
    }

    @Suppress("UNCHECKED_CAST")
    private fun createDataSource(): DataSource {
        val sqliteDriver = Class.forName("org.sqlite.JDBC")

        val dataSource = SimpleDriverDataSource()
        dataSource.setDriverClass(sqliteDriver as Class<Driver>)
        dataSource.url = "jdbc:sqlite:" + IConstants.DEFAULT_DATABASE_FILE_NAME()

        if (!IConfig.databaseUsername().isNullOrBlank() && !IConfig.databasePassword().isNullOrBlank()) {
            dataSource.username = IConfig.databaseUsername()
            dataSource.password = IConfig.databasePassword()
        }

        return dataSource
    }

    private fun registryMapperXml(configuration: MybatisConfiguration) {
        val classLoader = MybatisConfigurer::class.java.classLoader
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
                    "Add a mapper xml ${targetFile.name}".logInfo()

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
                        "Add a mapper xml ${jarEntry.name}".logInfo()

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