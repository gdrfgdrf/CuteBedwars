package io.github.gdrfgdrf.cutebedwars.database.impl.common

import cn.pomit.mybatis.ProxyHandlerFactory
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import io.github.gdrfgdrf.cutebedwars.database.Database
import io.github.gdrfgdrf.cutebedwars.database.impl.DefaultDatabase
import io.github.gdrfgdrf.cutebedwars.database.impl.MybatisConfigurer
import io.github.gdrfgdrf.cutebedwars.database.impl.mapper.PlayerDataMapper

fun database(): DefaultDatabase {
    return Database.get() as DefaultDatabase
}