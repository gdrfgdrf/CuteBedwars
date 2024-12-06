package io.github.gdrfgdrf.cutebedwars.database.impl.mysql.common

import io.github.gdrfgdrf.cutebedwars.database.Database
import io.github.gdrfgdrf.cutebedwars.database.impl.mysql.DefaultMysqlDatabase

fun database(): DefaultMysqlDatabase {
    return Database.get() as DefaultMysqlDatabase
}