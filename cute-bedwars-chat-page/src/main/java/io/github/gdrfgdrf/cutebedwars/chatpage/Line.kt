package io.github.gdrfgdrf.cutebedwars.chatpage

import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ITranslationAgent

class Line(
    private val content: ITranslationAgent
) {

    fun send() {
        content.send("")
    }

}