package io.github.gdrfgdrf.cutebedwars.chatpage

import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILocalizationContext

class Page(
    private val lines: List<Line>
) {

    fun send() {
        lines.forEach {
            it.send()
        }
    }

}