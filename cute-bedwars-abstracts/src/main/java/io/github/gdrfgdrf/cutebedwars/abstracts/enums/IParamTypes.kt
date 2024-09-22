package io.github.gdrfgdrf.cutebedwars.abstracts.enums

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumService
import org.bukkit.command.CommandSender

@EnumService("param_types_enum")
interface IParamTypes {
    fun name_(): String
    fun tab(sender: CommandSender, args: Array<String>): MutableList<String> {
        return arrayListOf()
    }
    fun validate(sender: CommandSender, args: Array<String>, currentIndex: Int, any: Any): Boolean

    companion object {
        fun valueOf(name: String): IParamTypes = Mediator.valueOf(IParamTypes::class.java, name)!!
    }
}