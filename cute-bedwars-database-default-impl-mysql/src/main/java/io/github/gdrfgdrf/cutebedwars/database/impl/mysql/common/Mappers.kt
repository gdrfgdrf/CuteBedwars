package io.github.gdrfgdrf.cutebedwars.database.impl.mysql.common

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.cutebedwars.database.exception.DatabaseException
import io.github.gdrfgdrf.cutebedwars.database.impl.mysql.MySQLMybatisConfigurer
import io.github.gdrfgdrf.cutebedwars.database.impl.mysql.mapper.CreatableMapper
import java.util.concurrent.ConcurrentHashMap

object Mappers {
    private val map = ConcurrentHashMap<Class<out BaseMapper<*>>, BaseMapper<*>>()

    @Suppress("UNCHECKED_CAST")
    fun <T> getOrCreateMapper(clazz: Class<out BaseMapper<*>>): T {
        if (MySQLMybatisConfigurer.sqlSessionFactory == null) {
            throw DatabaseException("The database implementation has not been initialized")
        }
        if (map.contains(clazz)) {
            return map[clazz] as T
        }

        "Creating the mapper: $clazz".logInfo()
        val session = MySQLMybatisConfigurer.sqlSessionFactory!!.openSession(true)
        val mapper = session.getMapper(clazz)
        if (mapper is CreatableMapper) {
            mapper.createTable()
        }
        map[clazz] = mapper

        return mapper as T
    }

    fun clear() {
        map.clear()
    }
}