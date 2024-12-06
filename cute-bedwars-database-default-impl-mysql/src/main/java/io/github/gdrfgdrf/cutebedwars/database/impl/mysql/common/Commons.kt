package io.github.gdrfgdrf.cutebedwars.database.impl.mysql.common

import io.github.gdrfgdrf.cutebedwars.database.Database
import io.github.gdrfgdrf.cutebedwars.database.impl.mysql.DefaultMySQLDatabase

fun database(): DefaultMySQLDatabase {
    return Database.instance() as DefaultMySQLDatabase
}