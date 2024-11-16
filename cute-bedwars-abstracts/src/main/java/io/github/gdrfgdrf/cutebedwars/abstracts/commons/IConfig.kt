package io.github.gdrfgdrf.cutebedwars.abstracts.commons

import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("config")
interface IConfig {
    fun fulfill()
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
        fun instance(): IConfig? {
             return instance
        }
        operator fun <T> get(key: String): T {
            if (instance == null) {
                throw IllegalStateException("the config is null")
            }
            return instance!!.get<T>(key)
        }
    }
}