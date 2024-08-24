package io.github.gdrfgdrf.cutebedwars.abstracts.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.IParam
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumService

@EnumService("commands_enum")
interface ICommands {
    fun name_(): String
    fun string(): String
    fun onlyPlayer(): Boolean
    fun argsRange(): IntRange
    fun permissions(): IPermissions
    fun params(): Array<IParam>?
    fun get(): String

    companion object {
        fun valueOf(name: String): ICommands = Mediator.valueOf(ICommands::class.java, name)!!
        fun values(): Array<*> = Mediator.values(ICommands::class.java)!!

        private val map = HashMap<String, ICommands>()

        fun find(command: String): ICommands? {
            if (map.isEmpty()) {
                values().forEach {
                    map[(it as ICommands).string()] = it
                }
            }
            return map[command]
        }
    }
}