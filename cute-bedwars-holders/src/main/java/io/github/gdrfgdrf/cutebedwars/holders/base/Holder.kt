package io.github.gdrfgdrf.cutebedwars.holders.base

import java.util.concurrent.ConcurrentHashMap

abstract class Holder {
    protected val map = ConcurrentHashMap<String, Any>()

    abstract fun set(name: String, any: Any)
    abstract fun <T> get(name: String): T
}