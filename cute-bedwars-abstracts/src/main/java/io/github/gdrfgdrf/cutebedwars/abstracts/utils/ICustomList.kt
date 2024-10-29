package io.github.gdrfgdrf.cutebedwars.abstracts.utils

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("custom_list", singleton = false)
interface ICustomList<E> {
    val list: MutableList<E>

    fun add(vararg e: E)
    fun remove(e: E)
    fun removeAt(index: Int)
    fun removeAll(vararg e: E)

    companion object {
        fun <E> new(): ICustomList<E> = Mediator.get(ICustomList::class.java)!!
    }
}