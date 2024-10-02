package io.github.gdrfgdrf.cutebedwars.abstracts.database

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("database")
interface IDatabase {
    fun initialize()
    fun close()

    companion object {
        fun instance(): IDatabase = Mediator.get(IDatabase::class.java)!!
    }
}