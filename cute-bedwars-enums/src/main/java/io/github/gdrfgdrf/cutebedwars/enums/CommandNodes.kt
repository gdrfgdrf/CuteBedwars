package io.github.gdrfgdrf.cutebedwars.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommandNodes
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumServiceImpl

@EnumServiceImpl("command_nodes_enum")
enum class CommandNodes(
    val string: String,
    private val displayOnRootTab: Boolean,
    private val parent: CommandNodes? = null
) : ICommandNodes {
    ROOT("cbw", false),
    ALLOW_NO_ARGS_ON_ROOT("", false, ROOT),
    ARGS("args", false, ROOT),

    CREATE("create", true, ARGS),

    INFO("info", true, ARGS),

    EDIT("edit", true, ARGS);

    override fun string(): String = string
    override fun displayOnRootTab(): Boolean = displayOnRootTab
    override fun parent(): ICommandNodes? = parent

    fun getRaw(command: String): String {
        if (this == ROOT) {
            return "/${ROOT.string} $command"
        }
        if (parent == null) {
            return "/$string"
        }

        val nodes = arrayListOf<CommandNodes>()
        nodes.add(this)

        while (true) {
            val lastParent = nodes[nodes.size - 1]
            if (lastParent.parent != null) {
                nodes.add(lastParent.parent)
            } else {
                break
            }
        }

        nodes.remove(ROOT)

        val stringBuilder = StringBuilder()

        nodes.forEach { commandNode ->
            if (commandNode == ARGS || commandNode == ALLOW_NO_ARGS_ON_ROOT) {
                return@forEach
            }

            stringBuilder.append(commandNode.string)
                .append(" ")
        }
        stringBuilder.append(command)

        return "/${ROOT.string} $stringBuilder"
    }

    override fun get(command: String): String {
        if (this == ROOT) {
            return "/${ROOT.string} $command"
        }
        if (parent == null) {
            return "/$string"
        }

        val nodes = arrayListOf<CommandNodes>()
        nodes.add(this)

        while (true) {
            val lastParent = nodes[nodes.size - 1]
            if (lastParent.parent != null) {
                nodes.add(lastParent.parent)
            } else {
                break
            }
        }

        nodes.remove(ROOT)

        val stringBuilder = StringBuilder()
        var argsNode = false

        nodes.forEachIndexed { index, commandNode ->
            if (commandNode == ARGS || commandNode == ALLOW_NO_ARGS_ON_ROOT) {
                argsNode = true

                if (index >= nodes.size - 1) {
                    stringBuilder.append(command)
                        .append(" ")
                        .append("args")
                } else {
                    stringBuilder.append(command)
                        .append(" ")
                        .append("args")
                        .append(" ")
                }
                return@forEachIndexed
            }

            stringBuilder.append(commandNode.string)
                .append(" ")
        }

        if (!argsNode) {
            stringBuilder.append(command)
        }

        return "/${ROOT.string} $stringBuilder"
    }
}