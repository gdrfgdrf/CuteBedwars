package io.github.gdrfgdrf.cutebedwars.abstracts.commons

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

        fun language(): String? = get()?.get("Language")
        fun workerId(): Short? = get()?.get("WorkerId")
        fun databaseImpl(): String? = get()?.get("DatabaseImpl")
        fun enableDatabaseLogging(): Boolean? = get()?.get("EnableDatabaseLogging")
        fun databaseUsername(): String? = get()?.get("DatabaseUsername")
        fun databasePassword(): String? = get()?.get("DatabasePassword")
        fun requestTimeout(): Long? = get()?.get("RequestTimeout")
    }
}