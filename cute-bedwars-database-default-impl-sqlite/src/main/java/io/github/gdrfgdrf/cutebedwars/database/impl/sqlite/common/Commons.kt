package io.github.gdrfgdrf.cutebedwars.database.impl.sqlite.common

import io.github.gdrfgdrf.cutebedwars.database.Database
import io.github.gdrfgdrf.cutebedwars.database.impl.sqlite.DefaultDatabase

fun database(): DefaultDatabase {
    return Database.get() as DefaultDatabase
}