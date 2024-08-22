package io.github.gdrfgdrf.cutebedwars.abstracts.enums

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumService
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@EnumService("request_type_enum")
interface IRequestTypes {
    fun name_(): String
    fun displayName(): String

    companion object {
        fun get(name: String): IRequestTypes = Mediator.get(IRequestTypes::class.java, ArgumentSet(arrayOf(name)))!!
    }
}