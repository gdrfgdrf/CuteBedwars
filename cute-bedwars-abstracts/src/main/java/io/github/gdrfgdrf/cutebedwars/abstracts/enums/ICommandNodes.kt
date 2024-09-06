package io.github.gdrfgdrf.cutebedwars.abstracts.enums

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumService

@EnumService("command_nodes_enum")
interface ICommandNodes {
    fun string(): String
    fun parent(): ICommandNodes?
    fun get(command: String): String

    companion object {
        fun valueOf(name: String): ICommandNodes = Mediator.valueOf(ICommandNodes::class.java, name)!!
        fun values(): Array<*> = Mediator.values(ICommandNodes::class.java)!!

        private val map = HashMap<String, ICommandNodes>()

        fun find(command: String): ICommandNodes? {
            if (map.isEmpty()) {
                synchronized(map) {
                    values().forEach {
                        map[(it as ICommandNodes).string()] = it
                    }
                }
            }
            return map[command]
        }
    }
}