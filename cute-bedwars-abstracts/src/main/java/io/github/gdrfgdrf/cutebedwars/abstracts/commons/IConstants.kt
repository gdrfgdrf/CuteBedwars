package io.github.gdrfgdrf.cutebedwars.abstracts.commons

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("constants")
@KotlinSingleton
interface IConstants {
    fun <T> get(key: String): T

    companion object {
        fun get(): IConstants = Mediator.get(IConstants::class.java)!!

        fun owner(): String = get().get("OWNER")
        fun baseFolder(): String = get().get("BASE_FOLDER")
        fun configFileName(): String = get().get("CONFIG_FILE_NAME")

        fun defaultDatabaseFileName(): String = get().get("DEFAULT_DATABASE_FILE_NAME")
        fun databaseImplDescriptionFileName(): String = get().get("DATABASE_IMPL_DESCRIPTION_FILE_NAME")
        fun customDatabaseImplFolderName(): String = get().get("CUSTOM_DATABASE_IMPL_FOLDER_NAME")

        fun areaFolder(): String = get().get("AREA_FOLDER")

        fun globalTimeout(): Long = get().get("GLOBAL_TIMEOUT")
    }
}