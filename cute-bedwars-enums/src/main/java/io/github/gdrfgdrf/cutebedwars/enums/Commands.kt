package io.github.gdrfgdrf.cutebedwars.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IParamScheme
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommandNodes
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPermissions
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumServiceImpl

@EnumServiceImpl("commands_enum")
enum class Commands(
    val string: String,
    private val onlyPlayer: Boolean = true,
    private val argsRange: IntRange,
    private val permissions: Permissions,
    private val allowEmptyParam: Boolean,
    private val node: CommandNodes = CommandNodes.ROOT,
    private val paramSchemes: Array<IParamScheme>? = null,
) : ICommands {
    ROOT("cbw", false, 0..Int.MAX_VALUE, Permissions.ROOT, true),
    HELP("help", false, 0..0, Permissions.HELP, true),
    RELOAD("reload", false, 0..0, Permissions.RELOAD, true),
    QUERY_DESCRIPTION(
        "query-description", false, 0..2, Permissions.QUERY_DESCRIPTION, true, CommandNodes.ALLOW_NO_ARGS_ON_ROOT,
        arrayOf(
            IParamScheme.get {
                add("PAGE_INDEX", "POSITIVE_NUMBER")
            },
            IParamScheme.get {
                add("DESCRIPTION", "NOT_BLANK_STRING")
            },
            IParamScheme.get {
                add("DESCRIPTION", "NOT_BLANK_STRING")
                add("PAGE_INDEX", "POSITIVE_NUMBER")
            }
        )
    ),
    INFO_COMMANDS("commands", false, 0..1, Permissions.INFO_COMMANDS, true, CommandNodes.INFO,
        arrayOf(
            IParamScheme.get {
                add("PAGE_INDEX", "POSITIVE_NUMBER")
            }
        )
    ),

    CREATE_AREA(
        "area", false, 1..1, Permissions.CREATE_AREA, false, CommandNodes.CREATE,
        arrayOf(
            IParamScheme.get {
                add("AREA_NAME", "NOT_BLANK_STRING")
            }
        )
    ),
    INFO_AREA(
        "area", false, 0..3, Permissions.INFO_AREA, true, CommandNodes.INFO,
        arrayOf(
            IParamScheme.get {
                add("PAGE_INDEX", "POSITIVE_NUMBER")
            },
            IParamScheme.get {
                add("FIND_BY_ID_OR_NAME", "FIND_BY_ID_OR_NAME")
                add("AREA", "AREAS")
            },
            IParamScheme.get {
                add("FIND_BY_ID_OR_NAME", "FIND_BY_ID_OR_NAME")
                add("AREA", "AREAS")
                add("PAGE_INDEX", "POSITIVE_NUMBER")
            }
        )
    ),
    EDIT_AREA(
        "area", false, 0..2, Permissions.EDIT_AREA, false, CommandNodes.EDIT,
        arrayOf(
            IParamScheme.get {
                add("FIND_BY_ID_OR_NAME", "FIND_BY_ID_OR_NAME")
                add("AREA", "AREAS")
            }
        )
    ),

    CREATE_GAME(
        "game", false, 3..3, Permissions.CREATE_GAME, false, CommandNodes.CREATE,
        arrayOf(
            IParamScheme.get {
                add("FIND_BY_ID_OR_NAME", "FIND_BY_ID_OR_NAME")
                add("AREA", "AREAS")
                add("GAME_NAME", "NOT_BLANK_STRING")
            }
        )
    ),
    INFO_GAME(
        "game", false, 2..5, Permissions.INFO_GAME, false, CommandNodes.INFO,
        arrayOf(
            IParamScheme.get {
                add("FIND_BY_ID_OR_NAME", "FIND_BY_ID_OR_NAME")
                add("AREA", "AREAS")
            },
            IParamScheme.get {
                add("FIND_BY_ID_OR_NAME", "FIND_BY_ID_OR_NAME")
                add("AREA", "AREAS")
                add("PAGE_INDEX", "POSITIVE_NUMBER")
            },
            IParamScheme.get {
                add("FIND_BY_ID_OR_NAME", "FIND_BY_ID_OR_NAME")
                add("AREA", "AREAS")
                add("FIND_BY_ID_OR_NAME", "FIND_BY_ID_OR_NAME")
                add("GAME", "GAMES")
            },
            IParamScheme.get {
                add("FIND_BY_ID_OR_NAME", "FIND_BY_ID_OR_NAME")
                add("AREA", "AREAS")
                add("FIND_BY_ID_OR_NAME", "FIND_BY_ID_OR_NAME")
                add("GAME", "GAMES")
                add("PAGE_INDEX", "POSITIVE_NUMBER")
            }
        )
    )

    ;

    override fun name_(): String = name
    override fun string(): String = string
    override fun onlyPlayer(): Boolean = onlyPlayer
    override fun argsRange(): IntRange = argsRange
    override fun permissions(): IPermissions = permissions
    override fun allowEmptyParam(): Boolean = allowEmptyParam
    override fun node(): ICommandNodes = node
    override fun paramsSchemes(): Array<IParamScheme>? = paramSchemes

    override fun getRaw(): String {
        if (this == ROOT) {
            return "/cbw"
        }
        val part = node.getRaw(string)
        return part
    }

    override fun getShort(): String {
        if (this == ROOT) {
            return "/cbw"
        }
        val part = node.get(string)

        if (paramSchemes.isNullOrEmpty()) {
            return part
        }
        return "$part ${getFirstParams()}"
    }

    override fun get(): String {
        if (this == ROOT) {
            return "/cbw"
        }
        val part = node.get(string)

        if (paramSchemes.isNullOrEmpty()) {
            return part
        }
        return "$part ${getWithParams()}"
    }

    private fun getFirstParams(): String {
        if (paramSchemes.isNullOrEmpty()) {
            return get()
        }
        return paramSchemes[0].get()
    }

    private fun getWithParams(): String {
        if (paramSchemes.isNullOrEmpty()) {
            return get()
        }
        val stringBuilder = StringBuilder()

        paramSchemes.forEachIndexed { index, paramScheme ->
            stringBuilder.append(paramScheme.get())

            if (index != paramSchemes.size - 1) {
                stringBuilder.append("|")
            }
        }

        return stringBuilder.toString()
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