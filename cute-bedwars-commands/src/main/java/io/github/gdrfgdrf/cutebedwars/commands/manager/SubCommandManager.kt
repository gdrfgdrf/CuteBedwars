package io.github.gdrfgdrf.cutebedwars.commands.manager

import io.github.gdrfgdrf.cutebedwars.commands.base.SubCommand
import io.github.gdrfgdrf.cutebedwars.commons.enums.Commands
import io.github.gdrfgdrf.cutebedwars.commons.extension.logInfo
import io.github.gdrfgdrf.cuteframework.utils.ClassUtils
import java.net.URL
import java.util.*

object SubCommandManager {
    private val map = LinkedHashMap<Commands, SubCommand>()

    fun scanAndRegister() {
        val classes = LinkedHashSet<Class<*>>()
        val urlEnumeration: Enumeration<URL> = SubCommandManager::class.java.classLoader
            .getResources("io.github.gdrfgdrf.cutebedwars.commands.sub".replace(".", "/"))

        println("============================")
        while (urlEnumeration.hasMoreElements()) {
            val url = urlEnumeration.nextElement()
            println(url)
        }
        println("============================")

        ClassUtils.searchJar(SubCommandManager::class.java.classLoader, "io.github.gdrfgdrf.cutebedwars.commands.sub", {
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

    fun clear() {
        map.clear()
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

    fun forEachUser(runnable: (Commands, SubCommand) -> Unit) {
        map.forEach { (commands, subCommand) ->
            if (commands.permissions.needOps()) {
                return@forEach
            }
            runnable(commands, subCommand)
        }
    }

    fun forEachAdmin(runnable: (Commands, SubCommand) -> Unit) {
        map.forEach { (commands, subCommand) ->
            if (!commands.permissions.needOps()) {
                return@forEach
            }
            runnable(commands, subCommand)
        }
    }
}