package io.github.gdrfgdrf.cutebedwars.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IDescriptions
import io.github.gdrfgdrf.cutebedwars.languages.collect.DescriptionLanguage
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumServiceImpl
import java.util.regex.Pattern

@EnumServiceImpl("descriptions_enum", searcher = "search")
enum class Descriptions(
    val value: () -> LanguageString?,
    val administration: Boolean = false
): IDescriptions {
    DESCRIPTION(DescriptionLanguage::DESCRIPTION),

    LANGUAGE(DescriptionLanguage::LANGUAGE, true),
    DATABASE_IMPL(DescriptionLanguage::DATABASE_IMPL, true),
    ENABLE_DATABASE_LOGGING(DescriptionLanguage::ENABLE_DATABASE_LOGGING, true),
    DATABASE_USERNAME(DescriptionLanguage::DATABASE_USERNAME, true),
    DATABASE_PASSWORD(DescriptionLanguage::DATABASE_PASSWORD, true),
    REQUEST_TIMEOUT(DescriptionLanguage::REQUEST_TIMEOUT, true),
    AREA_AUTO_SAVE_DELAY(DescriptionLanguage::AREA_AUTO_SAVE_DELAY, true),

    AREA_ID(DescriptionLanguage::AREA_ID),
    AREA_NAME(DescriptionLanguage::AREA_NAME),

    ;

    override fun name_(): String = name
    override fun value(): () -> LanguageString? = value
    override fun administration(): Boolean = administration

    companion object {
        @JvmStatic
        fun search(name: String): List<Descriptions> {
            return searchInternal(name).distinct().toList()
        }

        private fun searchInternal(name: String): List<Descriptions> {
            val result = arrayListOf<Descriptions>()

            val pattern = Pattern.compile(name, Pattern.CASE_INSENSITIVE)
            entries.forEach {
                val matcher = pattern.matcher(it.name_())
                if (matcher.find()) {
                    result.add(it)
                }
            }

            return result
        }
    }
}