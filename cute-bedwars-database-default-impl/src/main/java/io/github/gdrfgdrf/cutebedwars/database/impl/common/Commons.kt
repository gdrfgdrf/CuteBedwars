package io.github.gdrfgdrf.cutebedwars.database.impl.common

import io.github.gdrfgdrf.cutebedwars.database.Database
import io.github.gdrfgdrf.cutebedwars.database.impl.DefaultDatabase

fun database(): DefaultDatabase {
    return Database.get() as DefaultDatabase
}