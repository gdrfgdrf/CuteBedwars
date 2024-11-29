package io.github.gdrfgdrf.cutebedwars.locale

import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILanguageString
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.IOperableLanguageString
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@ServiceImpl("language_string", needArgument = true)
class LanguageString(override val text: String) : ILanguageString {
    constructor(argumentSet: ArgumentSet): this(argumentSet.args[0] as String)

    override fun operate(): IOperableLanguageString {
        return OperableLanguageString(text)
    }
}