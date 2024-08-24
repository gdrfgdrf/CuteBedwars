package io.github.gdrfgdrf.cutebedwars.abstracts.enums

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumService

@EnumService("request_type_enum")
interface IRequestTypes {
    fun name_(): String
    fun displayName(): String

    companion object {
        fun valueOf(name: String): IRequestTypes = Mediator.valueOf(IRequestTypes::class.java, name)!!
    }
}