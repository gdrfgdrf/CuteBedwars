package io.github.gdrfgdrf.cutebedwars.abstracts.commons

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("config")
interface IConfig {
    fun <T> get(key: String): T

    companion object {
        private var instance: IConfig? = null

        fun set(config: IConfig) {
            val clazz = Class.forName("io.github.gdrfgdrf.cutebedwars.commons.Config")
            val field = clazz.getField("INSTANCE")

            field.set(null, config)
            instance = config
        }
        @JvmStatic
        fun get(): IConfig? {
             return instance
        }

        fun getLanguage(): String? = get()?.get("Language")
        fun getWorkerId(): Short? = get()?.get("WorkerId")
        fun getDatabaseImpl(): String? = get()?.get("DatabaseImpl")
        fun getEnableDatabaseLogging(): Boolean? = get()?.get("EnableDatabaseLogging")
        fun getDatabaseUsername(): String? = get()?.get("DatabaseUsername")
        fun getDatabasePassword(): String? = get()?.get("DatabasePassword")
        fun getRequestTimeout(): Long? = get()?.get("RequestTimeout")
    }
}