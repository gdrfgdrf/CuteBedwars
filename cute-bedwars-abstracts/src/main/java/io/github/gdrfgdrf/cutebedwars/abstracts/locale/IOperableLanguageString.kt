package io.github.gdrfgdrf.cutebedwars.abstracts.locale

import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("operable_language_string", singleton = false)
interface IOperableLanguageString {
    val string: String
}