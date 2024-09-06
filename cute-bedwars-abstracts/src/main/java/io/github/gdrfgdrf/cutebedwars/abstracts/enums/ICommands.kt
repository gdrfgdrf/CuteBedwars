package io.github.gdrfgdrf.cutebedwars.abstracts.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IParamScheme
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumService

@EnumService("commands_enum")
interface ICommands {
    fun name_(): String
    fun string(): String
    fun onlyPlayer(): Boolean
    fun argsRange(): IntRange
    fun permissions(): IPermissions
    fun allowEmptyParam(): Boolean
    fun node(): ICommandNodes
    fun paramsSchemes(): Array<IParamScheme>?
    fun get(): String

    companion object {
        fun valueOf(name: String): ICommands = Mediator.valueOf(ICommands::class.java, name)!!
        fun values(): Array<*> = Mediator.values(ICommands::class.java)!!

        private val map = HashMap<String, MutableList<ICommands>>()

        fun find(string: String, node: ICommandNodes): ICommands? {
            if (map.isEmpty()) {
                synchronized(map) {
                    values().forEach {
                        val list = map.computeIfAbsent((it as ICommands).string()) {
                            ArrayList()
                        }
                        list.add(it)
                    }
                }
            }
            map[string]?.let { list ->
                val command = list.stream()
                    .filter { commands ->
                        commands.node() == node
                    }
                    .findAny()
                    .orElse(null)

                if (command != null) {
                    return command
                }
            }

            return null
        }
    }
}