package io.github.gdrfgdrf.cutebedwars.command.manager

import io.github.gdrfgdrf.cutebedwars.command.base.SubCommand
import io.github.gdrfgdrf.cutebedwars.commons.enums.Commands
import io.github.gdrfgdrf.cutebedwars.commons.extension.logInfo
import io.github.gdrfgdrf.cuteframework.utils.ClassUtils

object SubCommandManager {
    private val map = LinkedHashMap<Commands, SubCommand>()

    fun scanAndRegister() {
        val classes = HashSet<Class<*>>()
        ClassUtils.searchJar(SubCommandManager::class.java.classLoader, "io.github.gdrfgdrf.cutebedwars.command.sub", {
            return@searchJar it.superclass == SubCommand::class.java
        }, classes)

        classes.forEach {
            val field = it.getDeclaredField("INSTANCE")
            field.isAccessible = true
            val subCommand = field.get(null)

            "Registering the sub command ${(subCommand as SubCommand).command.string}".logInfo()

            register(subCommand)
        }
    }

    fun register(subCommand: SubCommand) {
        map[subCommand.command] = subCommand
    }

    fun get(command: String): SubCommand? {
        val commands = Commands.get(command) ?: return null
        return map[commands]
    }

    fun get(commands: Commands): SubCommand? {
        return map[commands]
    }

    fun filterAndFindFirst(filter: (Commands, SubCommand) -> Boolean): Pair<Commands, SubCommand>? {
        for (entry in map) {
            val commands = entry.key
            val subCommand = entry.value

            if (filter(commands, subCommand)) {
                return Pair(commands, subCommand)
            }
        }
        return null
    }

    fun forEach(runnable: (Commands, SubCommand) -> Unit) {
        map.forEach { (commands, subCommand) ->
            runnable(commands, subCommand)
        }
    }
}