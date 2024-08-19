package io.github.gdrfgdrf.cutebedwars.holders.base

abstract class Holder {
    protected val map = HashMap<String, Any>()

    abstract fun set(name: String, any: Any)
    abstract fun <T> get(name: String): T
}