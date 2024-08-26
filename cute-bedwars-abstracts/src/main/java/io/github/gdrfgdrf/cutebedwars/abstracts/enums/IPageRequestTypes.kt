package io.github.gdrfgdrf.cutebedwars.abstracts.enums

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumService

@EnumService("page_request_types")
interface IPageRequestTypes {
    fun cache(): Boolean

    companion object {
        fun valueOf(name: String): IPageRequestTypes = Mediator.valueOf(IPageRequestTypes::class.java, name)!!
    }

}