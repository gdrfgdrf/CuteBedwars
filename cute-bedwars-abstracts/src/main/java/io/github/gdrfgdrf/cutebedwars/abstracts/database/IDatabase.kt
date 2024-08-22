package io.github.gdrfgdrf.cutebedwars.abstracts.database

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("database")
@KotlinSingleton
interface IDatabase {
    fun initialize()
    fun close()

    companion object {
        fun get(): IDatabase = Mediator.get(IDatabase::class.java)!!
    }
}