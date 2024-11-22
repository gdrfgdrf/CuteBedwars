package io.github.gdrfgdrf.cutebedwars.utils

import io.github.gdrfgdrf.cutebedwars.abstracts.utils.ICustomList
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl

@ServiceImpl("custom_list")
class CustomList<E> : ICustomList<E> {
    override val list = arrayListOf<E>()

    override fun add(vararg e: E) {
        list.addAll(e.toSet())
    }

    override operator fun set(index: Int, e: E) {
        list[index] = e
    }

    override fun remove(e: E) {
        list.remove(e)
    }

    override fun removeAt(index: Int) {
        list.removeAt(index)
    }

    override fun removeAll(vararg e: E) {
        list.removeAll(e.toSet())
    }
}