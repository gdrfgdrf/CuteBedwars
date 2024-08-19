package io.github.gdrfgdrf.cutebedwars.database.impl

import cn.pomit.mybatis.configuration.MybatisConfiguration
import cn.pomit.mybatis.configuration.MybatisProperties
import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder
import io.github.gdrfgdrf.cutebedwars.beans.Config
import io.github.gdrfgdrf.cutebedwars.commons.common.Constants
import io.github.gdrfgdrf.cutebedwars.database.impl.common.database
import org.apache.ibatis.builder.xml.XMLMapperBuilder
import org.apache.ibatis.logging.jdk14.Jdk14LoggingImpl
import org.apache.ibatis.logging.nologging.NoLoggingImpl
import org.apache.ibatis.mapping.Environment
import org.apache.ibatis.session.Configuration
import org.apache.ibatis.session.SqlSessionFactory
import org.apache.ibatis.session.SqlSessionFactoryBuilder
import org.apache.ibatis.transaction.TransactionFactory
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory
import org.springframework.jdbc.datasource.SimpleDriverDataSource
import java.sql.Driver
import javax.sql.DataSource

object MybatisConfigurer {
    var sqlSessionFactory: SqlSessionFactory? = null

    fun initialize() {
        val mybatisProperties = MybatisProperties("io.github.gdrfgdrf.cutebedwars.database.impl.mapper", createDataSource())
        initConfiguration(mybatisProperties)
    }

    private fun initConfiguration(mybatisProperties: MybatisProperties) {
        val transactionFactory = JdbcTransactionFactory()
        val environment = Environment(database().displayName, transactionFactory, mybatisProperties.dataSource)
        val configuration = Configuration(environment)

        DefaultDatabase.MAPPERS.forEach {
            configuration.addMapper(it)
        }

        if (Config.INSTANCE.enableDatabaseLogging == true) {
            configuration.logImpl = Jdk14LoggingImpl::class.java
        } else {
            configuration.logImpl = NoLoggingImpl::class.java
        }

        val field = MybatisConfiguration::class.java.getDeclaredField("sqlSessionFactory")
        field.isAccessible = true

        field.set(null, MybatisSqlSessionFactoryBuilder().build(configuration))
    }

//    private fun createSqlSessionFactory(): SqlSessionFactory {
//        val dataSource = createDataSource()
//        val transactionFactory = JdbcTransactionFactory()
//        val environment = Environment(database().displayName, transactionFactory, dataSource)
//        val configuration = MybatisConfiguration(environment)
//
//        DefaultDatabase.MAPPERS.forEach {
//            configuration.addMapper(it)
//        }
//        if (Config.INSTANCE.enabledDatabaseLogging == true) {
//            configuration.logImpl = Jdk14LoggingImpl::class.java
//        } else {
//            configuration.logImpl = NoLoggingImpl::class.java
//        }
//        registryMapperXml(configuration, "mappers")
//
//        return MybatisSqlSessionFactoryBuilder().build(configuration)
//    }

    @Suppress("UNCHECKED_CAST")
    private fun createDataSource(): DataSource {
        val sqliteDriver = Class.forName("org.sqlite.JDBC")

        val dataSource = SimpleDriverDataSource()
        dataSource.setDriverClass(sqliteDriver as Class<Driver>)
        dataSource.url = "jdbc:sqlite:" + Constants.DEFAULT_DATABASE_FILE_NAME

        val config = Config.INSTANCE
        if (!config.databaseUsername.isNullOrBlank() && !config.databasePassword.isNullOrBlank()) {
            dataSource.username = config.databaseUsername
            dataSource.password = config.databasePassword
        }

        return dataSource
    }

}