package io.github.gdrfgdrf.cutebedwars.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPermissions
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumServiceImpl
import kotlin.enums.EnumEntries

@EnumServiceImpl("commands_enum")
enum class Commands(
    val string: String,
    val onlyPlayer: Boolean = true,
    val argsRange: IntRange,
    val permissions: Permissions,
): ICommands {
    ROOT("cbw", false, 0..Int.MAX_VALUE, Permissions.ROOT),
    HELP("help", false, 0..0, Permissions.HELP),
    RELOAD("reload", false, 0..0, Permissions.RELOAD),
    CREATE_AREA("create-area", false, 1..1, Permissions.CREATE_AREA)

    ;

    override fun name_(): String {
        return name
    }

    override fun string(): String {
        return string
    }

    override fun onlyPlayer(): Boolean {
        return onlyPlayer
    }

    override fun argsRange(): IntRange {
        return argsRange
    }

    override fun permissions(): IPermissions {
        return permissions
    }

    override fun get(): String {
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