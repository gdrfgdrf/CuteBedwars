package io.github.gdrfgdrf.cutebedwars.abstracts.locale

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@Service("language_string", singleton = false)
interface ILanguageString {
    val text: String

    fun operate(): IOperableLanguageString

    companion object {
        fun new(text: String): ILanguageString = Mediator.get(
            ILanguageString::class.java,
            ArgumentSet(arrayOf(text))
        )!!

        fun create(text: String): ILanguageString = new(text)
    }
}