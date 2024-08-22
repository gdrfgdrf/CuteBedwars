package io.github.gdrfgdrf.cutebedwars.locale

import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILocalizationContext
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import org.bukkit.command.CommandSender

@ServiceImpl("localization_context")
class LocalizationContext(argumentSet: ArgumentSet) : ILocalizationContext {
    private val sender: CommandSender = argumentSet.args[0] as CommandSender

    constructor(sender: CommandSender) :
            this(ArgumentSet(arrayOf(sender)))

    override fun message(string: String): LocalizationMessage {
        return LocalizationMessage(sender, string)
    }

    override fun message(languageString: LanguageString): LocalizationMessage {
        return LocalizationMessage(sender, languageString.get().string)
    }
}