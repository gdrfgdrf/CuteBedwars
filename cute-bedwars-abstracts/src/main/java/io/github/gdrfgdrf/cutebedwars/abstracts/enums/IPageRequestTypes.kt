package io.github.gdrfgdrf.cutebedwars.abstracts.enums

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumService

@EnumService("page_request_types")
interface IPageRequestTypes {

    companion object {
        fun valueOf(name: String): IPageRequestTypes = Mediator.valueOf(IPageRequestTypes::class.java, name)!!
    }

}