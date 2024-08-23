package io.github.gdrfgdrf.cutebedwars.abstracts.enums

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumService
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@EnumService("param_types_enum")
interface IParamTypes {
    fun name_(): String
    fun validate(any: Any): Boolean

    companion object {
        fun get(name: String): IParamTypes = Mediator.get(IParamTypes::class.java, ArgumentSet(arrayOf(name)))!!
    }
}