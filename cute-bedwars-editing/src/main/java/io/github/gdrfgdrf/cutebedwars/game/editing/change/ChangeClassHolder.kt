package io.github.gdrfgdrf.cutebedwars.game.editing.change

import io.github.gdrfgdrf.cutebedwars.abstracts.editing.change.AbstractChange
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.change.IChangeClassHolder
import io.github.gdrfgdrf.cutebedwars.game.editing.change.data.ChangeData
import io.github.gdrfgdrf.cutebedwars.game.editing.change.data.ChangeMetadata

class ChangeClassHolder<T : AbstractChange<*>>(
    private val clazz: Class<T>,
    private val metadata: ChangeMetadata
) : IChangeClassHolder<T> {
    override val type: Class<*> = metadata.type

    override fun validateArgsLength(protobuf: Boolean, vararg any: Any): Boolean {
        val argsRange = if (protobuf) {
            metadata.argsRange.first..metadata.maxArgsForProtobuf
        } else {
            metadata.argsRange
        }

        return argsRange.contains(any.size)
    }

    override fun create(vararg any: Any): T {
        val changeData = ChangeData.of(*any)

        val constructor = clazz.getConstructor(ChangeData::class.java)
        val instance = constructor.newInstance(changeData)

        return instance
    }

    companion object {
        fun <T : AbstractChange<*>> create(clazz: Class<T>, metadata: ChangeMetadata) =
            ChangeClassHolder(clazz, metadata)
    }
}