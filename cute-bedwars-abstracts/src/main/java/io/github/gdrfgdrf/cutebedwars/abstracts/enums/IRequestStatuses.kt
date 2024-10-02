package io.github.gdrfgdrf.cutebedwars.abstracts.enums

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumService

@EnumService("request_statuses_enum")
interface IRequestStatuses {
    companion object {
        fun valueOf(name: String): IRequestStatuses = Mediator.valueOf(IRequestStatuses::class.java, name)!!
    }
}