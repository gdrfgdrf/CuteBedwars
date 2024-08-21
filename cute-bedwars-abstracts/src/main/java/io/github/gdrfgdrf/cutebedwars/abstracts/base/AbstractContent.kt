package io.github.gdrfgdrf.cutebedwars.abstracts.base

import io.github.gdrfgdrf.cutebedwars.abstracts.manager.AbstractManager

abstract class AbstractContent(
    val clazz: Class<out AbstractContent>
) {
    init {
        register()
    }

    @Suppress("UNCHECKED_CAST")
    fun register() {
        AbstractManager.register(clazz, this)
    }
}