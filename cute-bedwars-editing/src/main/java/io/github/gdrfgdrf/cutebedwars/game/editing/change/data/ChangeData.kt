package io.github.gdrfgdrf.cutebedwars.game.editing.change.data

class ChangeData(
    private val args: Array<Any?>
) {
    @Suppress("UNCHECKED_CAST")
    operator fun <T> get(index: Int): T {
        return args[index] as T
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun of(vararg any: Any) = ChangeData(any as Array<Any?>)
    }

}