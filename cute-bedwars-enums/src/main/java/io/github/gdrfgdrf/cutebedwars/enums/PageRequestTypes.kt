package io.github.gdrfgdrf.cutebedwars.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPageRequestTypes
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumServiceImpl

@EnumServiceImpl("page_request_types")
enum class PageRequestTypes(
    val cache: Boolean = true
) : IPageRequestTypes {
    DESCRIPTIONS,
    INFO_AREA(false),
    INFO_GAME(false)
    ;

    override fun cache(): Boolean = cache
}