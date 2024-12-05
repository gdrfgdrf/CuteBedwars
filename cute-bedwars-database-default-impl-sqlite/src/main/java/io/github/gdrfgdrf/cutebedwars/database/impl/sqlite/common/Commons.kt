package io.github.gdrfgdrf.cutebedwars.database.impl.sqlite.common

import io.github.gdrfgdrf.cutebedwars.database.Database
import io.github.gdrfgdrf.cutebedwars.database.impl.sqlite.DefaultSqliteDatabase

fun database(): DefaultSqliteDatabase {
    return Database.get() as DefaultSqliteDatabase
}