package io.github.gdrfgdrf.cutebedwars.abstracts.enums

import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumService
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@EnumService("descriptions_enum")
interface IDescriptions {
    fun name_(): String
    fun value(): () -> LanguageString?
    fun administration(): Boolean

    companion object {
        fun get(name: String): IDescriptions = Mediator.get(IDescriptions::class.java, ArgumentSet(arrayOf(name)))!!
        fun find(name: String): IDescriptions? {
            runCatching {
                return Mediator.get(IDescriptions::class.java, ArgumentSet(arrayOf(name)))
            }.onFailure {
            }
            return null
        }
        fun values(): Array<*> = Mediator.get(IDescriptions::class.java)!!
    }
}