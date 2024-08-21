package io.github.gdrfgdrf.cutebedwars.abstracts.manager

import io.github.gdrfgdrf.cutebedwars.abstracts.base.AbstractContent
import java.util.concurrent.ConcurrentHashMap

object AbstractManager {
    private val map = ConcurrentHashMap<Class<out AbstractContent>, AbstractContent>()

    fun register(clazz: Class<out AbstractContent>, abstractContent: AbstractContent) {
        map[clazz] = abstractContent
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : AbstractContent> get(clazz: Class<out AbstractContent>): T {
        return map[clazz] as T
    }
}