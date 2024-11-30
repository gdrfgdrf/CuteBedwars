package io.github.gdrfgdrf.cutebedwars.commands.manager

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.ISubCommandManager
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommandNodes
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.AbstractSubCommand
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IClasses
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import java.util.*

@ServiceImpl("subcommand_manager")
object SubCommandManager : ISubCommandManager {
    private val map = LinkedHashMap<ICommands, AbstractSubCommand>()

    override fun scanAndRegister() {
        val classes = LinkedHashSet<Class<*>>()

        IClasses.instance().search(
            SubCommandManager::class.java.classLoader,
            "io.github.gdrfgdrf.cutebedwars.commands.sub",
            classes
        ) {
            return@search it.superclass == AbstractSubCommand::class.java
        }

        classes.forEach {
            val field = it.getDeclaredField("INSTANCE")
            field.isAccessible = true
            val subCommand = field.get(null)

            register(subCommand as AbstractSubCommand)
        }
    }

    private fun register(subCommand: AbstractSubCommand) {
        "Registering the sub command ${subCommand.command.getRaw()}".logInfo()
        map[subCommand.command] = subCommand
    }

    fun get(command: String, node: ICommandNodes = ICommandNodes.valueOf("ROOT")): AbstractSubCommand? {
        val commands = ICommands.find(command, node) ?: return null
        return map[commands]
    }

    override fun get(command: ICommands): AbstractSubCommand? {
        return map[command]
    }

    override fun clear() {
        map.clear()
    }

    fun search(filter: (ICommands, AbstractSubCommand) -> Boolean): Pair<ICommands, AbstractSubCommand>? {
        for (entry in map) {
            val commands = entry.key
            val subCommand = entry.value

            if (filter(commands, subCommand)) {
                return Pair(commands, subCommand)
            }
        }
        return null
    }

    fun forEach(runnable: (ICommands, AbstractSubCommand) -> Unit) {
        map.forEach { (commands, subCommand) ->
            runnable(commands, subCommand)
        }
    }

    fun forEachUser(runnable: (ICommands, AbstractSubCommand) -> Unit) {
        map.forEach { (commands, subCommand) ->
            if (commands.permissions.needOps()) {
                return@forEach
            }
            runnable(commands, subCommand)
        }
    }

    fun forEachAdmin(runnable: (ICommands, AbstractSubCommand) -> Unit) {
        map.forEach { (commands, subCommand) ->
            if (!commands.permissions.needOps()) {
                return@forEach
            }
            runnable(commands, subCommand)
        }
    }
}