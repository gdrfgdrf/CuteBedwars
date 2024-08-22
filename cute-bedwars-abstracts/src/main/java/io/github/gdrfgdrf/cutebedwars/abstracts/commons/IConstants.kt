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

        fun OWNER(): String = get().get("OWNER")
        fun BASE_FOLDER(): String = get().get("BASE_FOLDER")
        fun CONFIG_FILE_NAME(): String = get().get("CONFIG_FILE_NAME")

        fun DEFAULT_DATABASE_FILE_NAME(): String = get().get("DEFAULT_DATABASE_FILE_NAME")
        fun DATABASE_IMPL_DESCRIPTION_FILE_NAME(): String = get().get("DATABASE_IMPL_DESCRIPTION_FILE_NAME")
        fun CUSTOM_DATABASE_IMPL_FOLDER_NAME(): String = get().get("CUSTOM_DATABASE_IMPL_FOLDER_NAME")

        fun AREA_FOLDER(): String = get().get("AREA_FOLDER")

        fun GLOBAL_TIMEOUT(): Long = get().get("GLOBAL_TIMEOUT")
    }
}