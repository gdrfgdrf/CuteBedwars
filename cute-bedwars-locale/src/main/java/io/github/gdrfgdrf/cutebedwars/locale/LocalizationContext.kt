package io.github.gdrfgdrf.cutebedwars.locale

import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILocalizationContext
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ITranslationAgent
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ITranslationTextAgent
import io.github.gdrfgdrf.cutebedwars.locale.next.CuteTranslation
import io.github.gdrfgdrf.cutebedwars.locale.next.TranslationAgent
import io.github.gdrfgdrf.cutebedwars.locale.next.TranslationTextAgent
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import org.bukkit.command.CommandSender

@ServiceImpl("localization_context", needArgument = true)
class LocalizationContext(val sender: CommandSender) : ILocalizationContext {
    constructor(argumentSet: ArgumentSet): this(argumentSet.args[0] as CommandSender)

    override fun message(string: String): ITranslationAgent {
        return TranslationAgent(sender, CuteTranslation.of(string))
    }

    override fun message(languageString: LanguageString): ITranslationAgent {
        return TranslationAgent(sender, CuteTranslation.of(languageString.get().string))
    }

    override fun text(string: String): ITranslationTextAgent {
        return TranslationTextAgent.of(string)
    }

    override fun text(languageString: LanguageString): ITranslationTextAgent {
        return TranslationTextAgent.of(languageString.get().string)
    }
}