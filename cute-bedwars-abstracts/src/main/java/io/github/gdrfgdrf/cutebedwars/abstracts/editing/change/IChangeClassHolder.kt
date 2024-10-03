package io.github.gdrfgdrf.cutebedwars.abstracts.editing.change

interface IChangeClassHolder<T : AbstractChange<*>> {
    fun validateArgsLength(protobuf: Boolean = false, vararg any: Any): Boolean
    fun create(vararg any: Any): T
    fun type(): Class<*>
}