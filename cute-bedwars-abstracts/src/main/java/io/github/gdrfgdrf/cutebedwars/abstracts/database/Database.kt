package io.github.gdrfgdrf.cutebedwars.abstracts.database

import io.github.gdrfgdrf.cutebedwars.abstracts.base.AbstractContent
import io.github.gdrfgdrf.cutebedwars.abstracts.manager.AbstractManager

abstract class Database : AbstractContent(Database::class.java) {
    abstract fun initialize()
    abstract fun close()

    companion object {
        fun get(): Database = AbstractManager.get(Database::class.java)
    }
}