package io.github.gdrfgdrf.cutebedwars.game.editing.change

import io.github.gdrfgdrf.cutebedwars.abstracts.game.editing.change.AbstractChange
import io.github.gdrfgdrf.cutebedwars.abstracts.game.editing.change.IChangeClassHolder
import io.github.gdrfgdrf.cutebedwars.game.editing.change.data.ChangeData

class ChangeClassHolder<T : AbstractChange<*>>(private val clazz: Class<T>) : IChangeClassHolder<T> {
    override fun create(vararg any: Any): T {
        val changeData = ChangeData.of(*any)

        val constructor = clazz.getConstructor(ChangeData::class.java)
        val instance = constructor.newInstance(changeData)

        return instance
    }

    companion object {
        fun <T : AbstractChange<*>> create(clazz: Class<T>) = ChangeClassHolder(clazz)
    }
}