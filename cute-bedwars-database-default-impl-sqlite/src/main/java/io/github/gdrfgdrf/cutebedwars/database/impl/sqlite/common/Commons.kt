package io.github.gdrfgdrf.cutebedwars.database.impl.sqlite.common

import io.github.gdrfgdrf.cutebedwars.database.Database
import io.github.gdrfgdrf.cutebedwars.database.impl.sqlite.DefaultSQLiteDatabase

fun database(): DefaultSQLiteDatabase {
    return Database.get() as DefaultSQLiteDatabase
}