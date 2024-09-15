package io.github.gdrfgdrf.cutebedwars.commands.manager

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.ISubCommandManager
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommandNodes
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.ISubCommand
import io.github.gdrfgdrf.cutebedwars.utils.extension.logInfo
import io.github.gdrfgdrf.cuteframework.utils.ClassUtils
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import java.util.*

@ServiceImpl("subcommand_manager")
object SubCommandManager : ISubCommandManager {
    private val map = LinkedHashMap<ICommands, ISubCommand>()

    override fun scanAndRegister() {
        val classes = LinkedHashSet<Class<*>>()

        ClassUtils.searchJar(SubCommandManager::class.java.classLoader, "io.github.gdrfgdrf.cutebedwars.commands.sub", {
            return@searchJar it.superclass == ISubCommand::class.java
        }, classes)

        classes.forEach {
            val field = it.getDeclaredField("INSTANCE")
            field.isAccessible = true
            val subCommand = field.get(null)

            "Registering the sub command ${(subCommand as ISubCommand).command.string()}".logInfo()

            register(subCommand)
        }
    }

    fun register(subCommand: ISubCommand) {
        map[subCommand.command] = subCommand
    }

    fun get(command: String, node: ICommandNodes = ICommandNodes.valueOf("ROOT")): ISubCommand? {
        val commands = ICommands.find(command, node) ?: return null
        return map[commands]
    }

    override fun get(command: ICommands): ISubCommand? {
        return map[command]
    }

    override fun clear() {
        map.clear()
    }

    fun search(filter: (ICommands, ISubCommand) -> Boolean): Pair<ICommands, ISubCommand>? {
        for (entry in map) {
            val commands = entry.key
            val subCommand = entry.value

            if (filter(commands, subCommand)) {
                return Pair(commands, subCommand)
            }
        }
        return null
    }

    fun forEach(runnable: (ICommands, ISubCommand) -> Unit) {
        map.forEach { (commands, subCommand) ->
            runnable(commands, subCommand)
        }
    }

    fun forEachUser(runnable: (ICommands, ISubCommand) -> Unit) {
        map.forEach { (commands, subCommand) ->
            if (commands.permissions().needOps()) {
                return@forEach
            }
            runnable(commands, subCommand)
        }
    }

    fun forEachAdmin(runnable: (ICommands, ISubCommand) -> Unit) {
        map.forEach { (commands, subCommand) ->
            if (!commands.permissions().needOps()) {
                return@forEach
            }
            runnable(commands, subCommand)
        }
    }
}