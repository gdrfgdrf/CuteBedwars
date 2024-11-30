package io.github.gdrfgdrf.cutebedwars.abstracts.commons

import java.lang.reflect.Method

interface IConfig {
    fun fulfill()

    @Suppress("UNCHECKED_CAST")
    fun <T> get(key: String): T {
        val method: Method = this::class.java.getMethod("get$key")
        return method.invoke(this) as T
    }
}