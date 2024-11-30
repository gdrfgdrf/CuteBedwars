package io.github.gdrfgdrf.cutebedwars.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommandNodes
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumServiceImpl

@EnumServiceImpl("command_nodes_enum")
enum class CommandNodes(
    override val string: String,
    override val displayOnRootTab: Boolean,
    override val parent: CommandNodes? = null
) : ICommandNodes {
    ROOT("cbw", false),
    ARGS("args", false, ROOT),

    QUERY("query", true, ARGS),

    CREATE("create", true, ARGS),

    INFO("info", true, ARGS),

    EDITOR("editor", true, ARGS),
    EDIT("edit", true, ARGS),
    EDIT_LIST_COMMITS("list-commits", true, EDIT)

    ;

    fun getRaw(command: String): String {
        val nodes = arrayListOf<CommandNodes>()
        nodes.add(this)

        while (true) {
            val lastParent = nodes[nodes.size - 1]
            if (lastParent.parent != null) {
                nodes.add(lastParent.parent!!)
            } else {
                break
            }
        }
        nodes.reverse()

        val stringBuilder = StringBuilder()

        nodes.forEachIndexed { index, commandNodes ->
            if (commandNodes == ARGS) {
                return@forEachIndexed
            }

            if (index >= nodes.size - 1) {
                stringBuilder.append(commandNodes.string)
            } else {
                stringBuilder.append(commandNodes.string).append(" ")
            }
        }
        stringBuilder.append(" ")
            .append(command)

        return "/$stringBuilder"
    }

    override fun get(command: String): String {
        val nodes = arrayListOf<CommandNodes>()
        nodes.add(this)

        while (true) {
            val lastParent = nodes[nodes.size - 1]
            if (lastParent.parent != null) {
                nodes.add(lastParent.parent!!)
            } else {
                break
            }
        }
        nodes.reverse()

        val stringBuilder = StringBuilder()
        var appendArgs = false

        nodes.forEachIndexed { index, commandNodes ->
            if (commandNodes == ARGS) {
                appendArgs = true
                return@forEachIndexed
            }

            if (index >= nodes.size - 1) {
                stringBuilder.append(commandNodes.string)
            } else {
                stringBuilder.append(commandNodes.string).append(" ")
            }
        }
        if (appendArgs) {
            stringBuilder.append(" ")
                .append(command)
                .append(" ")
                .append(ARGS.string)
        } else {
            stringBuilder.append(" ")
                .append(command)
        }

        return "/$stringBuilder"
    }
}