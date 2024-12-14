package io.github.gdrfgdrf.cutebedwars.game.editing.change.data

class ChangeData(
    private val args: Array<Any?>
) {
    fun length(): Int = args.size

    @Suppress("UNCHECKED_CAST")
    operator fun <T> get(index: Int): T {
        return args[index] as T
    }

    fun <T> getOrNull(index: Int): T? {
        runCatching {
            return get(index)
        }.onFailure {
            return null
        }
        return null
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun of(vararg any: Any) = ChangeData(any as Array<Any?>)
    }

}