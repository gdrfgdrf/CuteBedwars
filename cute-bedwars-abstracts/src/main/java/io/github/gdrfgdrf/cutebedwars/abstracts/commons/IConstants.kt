package io.github.gdrfgdrf.cutebedwars.abstracts.commons

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("constants")
@KotlinSingleton
interface IConstants {
    fun <T> get(key: String): T

    companion object {
        fun instance(): IConstants = Mediator.get(IConstants::class.java)!!
        operator fun get(key: String): String = instance().get(key)

        fun baseFolder(): String = instance().get("BASE_FOLDER")
        fun configFileName(): String = instance().get("CONFIG_FILE_NAME")

        fun defaultDatabaseFileName(): String = instance().get("DEFAULT_DATABASE_FILE_NAME")
        fun databaseImplDescriptionFileName(): String = instance().get("DATABASE_IMPL_DESCRIPTION_FILE_NAME")
        fun customDatabaseImplFolderName(): String = instance().get("CUSTOM_DATABASE_IMPL_FOLDER_NAME")

        fun areaFolder(): String = instance().get("AREA_FOLDER")

        fun globalTimeout(): Long = instance().get("GLOBAL_TIMEOUT")
    }
}