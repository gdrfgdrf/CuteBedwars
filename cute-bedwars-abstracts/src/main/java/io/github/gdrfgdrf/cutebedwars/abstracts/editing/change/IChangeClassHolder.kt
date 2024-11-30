package io.github.gdrfgdrf.cutebedwars.abstracts.editing.change

interface IChangeClassHolder<T : AbstractChange<*>> {
    val type: Class<*>

    fun validateArgsLength(protobuf: Boolean = false, vararg any: Any): Boolean
    fun create(vararg any: Any): T
}