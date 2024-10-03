package io.github.gdrfgdrf.cutebedwars.chatpage

import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILocalizationContext
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILocalizationMessage
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ITranslationAgent
import io.github.gdrfgdrf.cuteframework.locale.LanguageString

class Line(
    private val content: ITranslationAgent
) {

    fun send() {
        content.send("")
    }

}