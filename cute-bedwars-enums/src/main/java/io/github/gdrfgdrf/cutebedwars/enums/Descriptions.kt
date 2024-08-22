package io.github.gdrfgdrf.cutebedwars.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IDescriptions
import io.github.gdrfgdrf.cutebedwars.languages.collect.DescriptionLanguage
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumServiceImpl

@EnumServiceImpl("descriptions_enum")
enum class Descriptions(
    val value: () -> LanguageString?
): IDescriptions {
    AREA_ID(DescriptionLanguage::AREA_ID),
    AREA_NAME(DescriptionLanguage::AREA_NAME)

    ;

    override fun name_(): String {
        return name
    }

    override fun value(): () -> LanguageString? {
        return value
    }
}