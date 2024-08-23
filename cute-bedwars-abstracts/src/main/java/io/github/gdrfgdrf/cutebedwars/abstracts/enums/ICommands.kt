package io.github.gdrfgdrf.cutebedwars.abstracts.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.IParam
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILocalizationContext
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumService
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import net.md_5.bungee.api.chat.BaseComponent

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
        fun get(name: String): ICommands = Mediator.get(ICommands::class.java, ArgumentSet(arrayOf(name)))!!
        fun values(): Array<*> = Mediator.get(ICommands::class.java)!!

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