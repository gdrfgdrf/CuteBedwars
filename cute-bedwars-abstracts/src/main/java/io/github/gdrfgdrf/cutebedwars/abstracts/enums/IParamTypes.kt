package io.github.gdrfgdrf.cutebedwars.abstracts.enums

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumService

@EnumService("param_types_enum")
interface IParamTypes {
    fun name_(): String
    fun tab(args: Array<String>): MutableList<String> {
        return arrayListOf()
    }
    fun validate(args: Array<String>, currentIndex: Int, any: Any): Boolean

    companion object {
        fun valueOf(name: String): IParamTypes = Mediator.valueOf(IParamTypes::class.java, name)!!
    }
}