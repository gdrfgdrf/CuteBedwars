package io.github.gdrfgdrf.cutebedwars.abstracts.requests

import io.github.gdrfgdrf.cutebedwars.abstracts.base.AbstractContent
import io.github.gdrfgdrf.cutebedwars.abstracts.manager.AbstractManager

abstract class Requests : AbstractContent(Requests::class.java) {
    abstract fun initialize()
    abstract fun reset()

    companion object {
        fun get(): Requests = AbstractManager.get(Requests::class.java)
    }
}