package io.github.gdrfgdrf.cutebedwars.abstracts.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILocalizationContext
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ITranslationTextAgent
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumService
import org.bukkit.command.CommandSender

@EnumService("descriptions_enum")
interface IDescriptions {
    fun name_(): String
    fun value(): () -> LanguageString?
    fun administration(): Boolean
    fun convenient(sender: CommandSender, localizationContext: ILocalizationContext): ITranslationTextAgent
    fun convenient(translationTextAgent: ITranslationTextAgent)

    companion object {
        fun valueOf(name: String): IDescriptions = Mediator.valueOf(IDescriptions::class.java, name)!!
        fun find(name: String): IDescriptions? {
            runCatching {
                return Mediator.valueOf(IDescriptions::class.java, name)
            }.onFailure {
            }
            return null
        }
        fun values(): Array<*> = Mediator.values(IDescriptions::class.java)!!
        fun search(name: String): List<IDescriptions>? = Mediator.search(IDescriptions::class.java, name)
    }
}