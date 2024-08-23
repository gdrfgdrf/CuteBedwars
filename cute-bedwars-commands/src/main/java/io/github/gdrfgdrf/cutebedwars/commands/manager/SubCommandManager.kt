package io.github.gdrfgdrf.cutebedwars.commands.manager

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.ISubCommandManager
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.commands.base.SubCommand
import io.github.gdrfgdrf.cutebedwars.utils.extension.logInfo
import io.github.gdrfgdrf.cuteframework.utils.ClassUtils
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import java.util.*

@ServiceImpl("subcommand_manager")
object SubCommandManager : ISubCommandManager {
    private val map = LinkedHashMap<ICommands, SubCommand>()

    override fun scanAndRegister() {
        val classes = LinkedHashSet<Class<*>>()

        ClassUtils.searchJar(SubCommandManager::class.java.classLoader, "io.github.gdrfgdrf.cutebedwars.commands.sub", {
            return@searchJar it.superclass == SubCommand::class.java
        }, classes)

        classes.forEach {
            val field = it.getDeclaredField("INSTANCE")
            field.isAccessible = true
            val subCommand = field.get(null)

            "Registering the sub command ${(subCommand as SubCommand).command.string()}".logInfo()

            register(subCommand)
        }
    }

    fun register(subCommand: SubCommand) {
        map[subCommand.command] = subCommand
    }

    fun get(command: String): SubCommand? {
        val commands = ICommands.find(command) ?: return null
        return map[commands]
    }

    fun get(commands: ICommands): SubCommand? {
        return map[commands]
    }

    override fun clear() {
        map.clear()
    }

    fun search(filter: (ICommands, SubCommand) -> Boolean): Pair<ICommands, SubCommand>? {
        for (entry in map) {
            val commands = entry.key
            val subCommand = entry.value

            if (filter(commands, subCommand)) {
                return Pair(commands, subCommand)
            }
        }
        return null
    }

    fun forEach(runnable: (ICommands, SubCommand) -> Unit) {
        map.forEach { (commands, subCommand) ->
            runnable(commands, subCommand)
        }
    }

    fun forEachUser(runnable: (ICommands, SubCommand) -> Unit) {
        map.forEach { (commands, subCommand) ->
            if (commands.permissions().needOps()) {
                return@forEach
            }
            runnable(commands, subCommand)
        }
    }

    fun forEachAdmin(runnable: (ICommands, SubCommand) -> Unit) {
        map.forEach { (commands, subCommand) ->
            if (!commands.permissions().needOps()) {
                return@forEach
            }
            runnable(commands, subCommand)
        }
    }
}