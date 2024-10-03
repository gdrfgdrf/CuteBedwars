package io.github.gdrfgdrf.cutebedwars.abstracts.locale

import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import org.bukkit.command.CommandSender

@Service("localization_context", singleton = false)
interface ILocalizationContext {
    fun message(string: String): ITranslationAgent
    fun message(languageString: LanguageString): ITranslationAgent

    companion object {
        fun new(sender: CommandSender): ILocalizationContext = Mediator.get(
            ILocalizationContext::class.java, ArgumentSet(
                arrayOf(sender)
            )
        )!!
    }
}