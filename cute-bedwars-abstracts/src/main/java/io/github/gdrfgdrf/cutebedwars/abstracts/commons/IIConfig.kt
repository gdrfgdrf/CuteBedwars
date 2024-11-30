package io.github.gdrfgdrf.cutebedwars.abstracts.commons

import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("config")
interface IIConfig {
    fun fulfill()
    fun <T> get(key: String): T

    companion object {
        private var instance: IIConfig? = null

        fun set(config: IIConfig) {
            val clazz = Class.forName("io.github.gdrfgdrf.cutebedwars.commons.Config")
            val field = clazz.getField("INSTANCE")

            field.set(null, config)
            instance = config
        }
        @JvmStatic
        fun instance(): IIConfig? {
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