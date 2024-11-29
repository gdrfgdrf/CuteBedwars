package io.github.gdrfgdrf.cutebedwars.locale

import io.github.gdrfgdrf.cutebedwars.abstracts.locale.IOperableLanguageString
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl

@ServiceImpl("operable_language_string", needArgument = true)
class OperableLanguageString(override var string: String) : IOperableLanguageString {

}