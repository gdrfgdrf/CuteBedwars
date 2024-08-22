package io.github.gdrfgdrf.cutebedwars.abstracts.enums

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumService
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@EnumService("request_statuses_enum")
interface IRequestStatuses {
    fun name_(): String

    companion object {
        fun get(name: String): IRequestStatuses = Mediator.get(IRequestStatuses::class.java, ArgumentSet(arrayOf(name)))!!
    }
}