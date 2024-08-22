package io.github.gdrfgdrf.cutebedwars.commons.enums

enum class Commands(
    val string: String,
    val onlyPlayer: Boolean = true,
    val argsRange: IntRange,
    val permissions: Permissions,
) {
    ROOT("cbw", false, 0..Int.MAX_VALUE, Permissions.ROOT),
    HELP("help", false, 0..0, Permissions.HELP),
    RELOAD("reload", false, 0..0, Permissions.RELOAD)

    ;

    fun get(): String {
        if (this == ROOT) {
            return "/cbw"
        }
        return "${ROOT.get()} $string"
    }

    companion object {
        private val map = HashMap<String, Commands>()
        init {
            entries.toTypedArray().forEach {
                map[it.string] = it
            }
        }

        fun get(command: String): Commands? {
            return map[command]
        }
    }
}