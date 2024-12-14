package io.github.gdrfgdrf.cutebedwars.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.IParamScheme
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumServiceImpl

@EnumServiceImpl("commands_enum")
enum class Commands(
    override val string: String,
    override val onlyPlayer: Boolean = true,
    override val argsRange: IntRange,
    override val permissions: Permissions,
    override val allowEmptyParam: Boolean,
    override val node: CommandNodes = CommandNodes.ROOT,
    override val paramSchemes: Array<IParamScheme>? = null,
) : ICommands {
    ROOT("cbw", false, 0..Int.MAX_VALUE, Permissions.ROOT, true),

    DEV("dev", false, 0..Int.MAX_VALUE, Permissions.DEV, true),

    HELP("help", false, 0..0, Permissions.HELP, true),
    RELOAD("reload", false, 0..0, Permissions.RELOAD, true),
    QUERY_DESCRIPTION(
        "description", false, 0..2, Permissions.QUERY_DESCRIPTION, true, CommandNodes.QUERY,
        arrayOf(
            IParamScheme.new {
                add("PAGE_INDEX", "POSITIVE_NUMBER")
            },
            IParamScheme.new {
                add("DESCRIPTION", "NOT_BLANK_STRING")
            },
            IParamScheme.new {
                add("DESCRIPTION", "NOT_BLANK_STRING")
                add("PAGE_INDEX", "POSITIVE_NUMBER")
            }
        )
    ),
    INFO_COMMANDS("commands", false, 0..1, Permissions.INFO_COMMANDS, true, CommandNodes.INFO,
        arrayOf(
            IParamScheme.new {
                add("PAGE_INDEX", "POSITIVE_NUMBER")
            },
            IParamScheme.new {
                add("COMMAND_ENUM", "NOT_BLANK_STRING")
            }
        )
    ),

    CREATE_AREA(
        "area", false, 1..1, Permissions.CREATE_AREA, false, CommandNodes.CREATE,
        arrayOf(
            IParamScheme.new {
                add("AREA_NAME", "NOT_BLANK_STRING")
            }
        )
    ),
    INFO_AREA(
        "area", false, 0..3, Permissions.INFO_AREA, true, CommandNodes.INFO,
        arrayOf(
            IParamScheme.new {
                add("PAGE_INDEX", "POSITIVE_NUMBER")
            },
            IParamScheme.new {
                add("FIND_BY_ID_OR_NAME", "FIND_BY_ID_OR_NAME")
                add("AREA", "AREAS")
            },
            IParamScheme.new {
                add("FIND_BY_ID_OR_NAME", "FIND_BY_ID_OR_NAME")
                add("AREA", "AREAS")
                add("PAGE_INDEX", "POSITIVE_NUMBER")
            }
        )
    ),
    EDITOR_AREA(
        "area", false, 2..2, Permissions.EDITOR_AREA, false, CommandNodes.EDITOR,
        arrayOf(
            IParamScheme.new {
                add("FIND_BY_ID_OR_NAME", "FIND_BY_ID_OR_NAME")
                add("AREA", "AREAS")
            }
        )
    ),

    CREATE_GAME(
        "game", false, 3..3, Permissions.CREATE_GAME, false, CommandNodes.CREATE,
        arrayOf(
            IParamScheme.new {
                add("FIND_BY_ID_OR_NAME", "FIND_BY_ID_OR_NAME")
                add("AREA", "AREAS")
                add("GAME_NAME", "NOT_BLANK_STRING")
            }
        )
    ),
    INFO_GAME(
        "game", false, 2..5, Permissions.INFO_GAME, false, CommandNodes.INFO,
        arrayOf(
            IParamScheme.new {
                add("FIND_BY_ID_OR_NAME", "FIND_BY_ID_OR_NAME")
                add("AREA", "AREAS")
            },
            IParamScheme.new {
                add("FIND_BY_ID_OR_NAME", "FIND_BY_ID_OR_NAME")
                add("AREA", "AREAS")
                add("PAGE_INDEX", "POSITIVE_NUMBER")
            },
            IParamScheme.new {
                add("FIND_BY_ID_OR_NAME", "FIND_BY_ID_OR_NAME")
                add("AREA", "AREAS")
                add("FIND_BY_ID_OR_NAME", "FIND_BY_ID_OR_NAME")
                add("GAME", "GAMES")
            },
            IParamScheme.new {
                add("FIND_BY_ID_OR_NAME", "FIND_BY_ID_OR_NAME")
                add("AREA", "AREAS")
                add("FIND_BY_ID_OR_NAME", "FIND_BY_ID_OR_NAME")
                add("GAME", "GAMES")
                add("PAGE_INDEX", "POSITIVE_NUMBER")
            }
        )
    ),
    EDITOR_GAME(
        "game", false, 4..4, Permissions.EDITOR_GAME, false, CommandNodes.EDITOR,
        arrayOf(
            IParamScheme.new {
                add("FIND_BY_ID_OR_NAME", "FIND_BY_ID_OR_NAME")
                add("AREA", "AREAS")
                add("FIND_BY_ID_OR_NAME", "FIND_BY_ID_OR_NAME")
                add("GAME", "GAMES")
            }
        )
    ),

    EDIT_NEW_CHANGES(
        "new-changes", false, 0..0, Permissions.EDIT_NEW_CHANGES, true, CommandNodes.EDIT,
    ),
    EDIT_MAKE(
        "make", false, 1..Int.MAX_VALUE, Permissions.EDIT_MAKE, false, CommandNodes.EDIT,
        arrayOf(
            IParamScheme.new {
                add("CHANGE_TYPE", "CHANGES")
            },
        )
    ),
    EDIT_UNMAKE(
        "unmake", false, 1..Int.MAX_VALUE, Permissions.EDIT_MAKE, false, CommandNodes.EDIT,
        arrayOf(
            IParamScheme.new {
                add("CHANGE_ID", "CHANGE_IDS")
            }
        )
    ),
    EDIT_LIST_CHANGES(
        "list-changes", false, 0..1, Permissions.EDIT_LIST_CHANGES, true, CommandNodes.EDIT,
        arrayOf(
            IParamScheme.new {
                add("PAGE_INDEX", "POSITIVE_NUMBER")
            }
        )
    ),
    EDIT_COMMIT(
        "commit", false, 0..1, Permissions.EDIT_COMMIT, true, CommandNodes.EDIT,
        arrayOf(
            IParamScheme.new {
                add("COMMIT_MESSAGE", "NOT_BLANK_STRING")
            }
        )
    ),
    EDIT_REVERT_COMMIT(
        "revert", false, 1..1, Permissions.EDIT_REVERT_COMMIT, false, CommandNodes.EDIT,
        arrayOf(
            IParamScheme.new {
                add("COMMIT_ID", "COMMIT_IDS")
            }
        )
    ),
    EDIT_EXIT(
        "exit", false, 0..0, Permissions.EDIT_EXIT, true, CommandNodes.EDIT,
    ),

    EDIT_LIST_AREA_COMMITS(
        "area", false, 2..3, Permissions.EDIT_LIST_AREA_COMMITS, false, CommandNodes.EDIT_LIST_COMMITS,
        arrayOf(
            IParamScheme.new {
                add("FIND_BY_ID_OR_NAME", "FIND_BY_ID_OR_NAME")
                add("AREA", "AREAS")
            },
            IParamScheme.new {
                add("FIND_BY_ID_OR_NAME", "FIND_BY_ID_OR_NAME")
                add("AREA", "AREAS")
                add("PAGE_INDEX", "POSITIVE_NUMBER")
            }
        )
    ),
    EDIT_LIST_GAME_COMMITS(
        "game", false, 4..5, Permissions.EDIT_LIST_GAME_COMMITS, false, CommandNodes.EDIT_LIST_COMMITS,
        arrayOf(
            IParamScheme.new {
                add("FIND_BY_ID_OR_NAME", "FIND_BY_ID_OR_NAME")
                add("AREA", "AREAS")
                add("FIND_BY_ID_OR_NAME", "FIND_BY_ID_OR_NAME")
                add("GAME", "GAMES")
            },
            IParamScheme.new {
                add("FIND_BY_ID_OR_NAME", "FIND_BY_ID_OR_NAME")
                add("AREA", "AREAS")
                add("FIND_BY_ID_OR_NAME", "FIND_BY_ID_OR_NAME")
                add("GAME", "GAMES")
                add("PAGE_INDEX", "POSITIVE_NUMBER")
            }
        )
    )

    ;

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
            if (part.endsWith(" args")) {
                return part.substring(0, part.indexOf(" args"))
            }

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
        return paramSchemes!![0].content()
    }

    private fun getWithParams(): String {
        if (paramSchemes.isNullOrEmpty()) {
            return get()
        }
        val stringBuilder = StringBuilder()

        paramSchemes!!.forEachIndexed { index, paramScheme ->
            stringBuilder.append(paramScheme.content())

            if (index != paramSchemes!!.size - 1) {
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