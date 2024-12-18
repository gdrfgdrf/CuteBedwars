package io.github.gdrfgdrf.cutebedwars.editing.change.data

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

    fun getStringOrBlank(index: Int): String {
        runCatching {
            return get(index)
        }.onFailure {
            return ""
        }
        return ""
    }

    fun getStringOrDefault(index: Int, default: String): String {
        runCatching {
            return get(index)
        }.onFailure {
            return default
        }
        return default
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun of(vararg any: Any) = ChangeData(any as Array<Any?>)
    }

}