package io.github.gdrfgdrf.cutebedwars.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPageRequestTypes
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumServiceImpl

@EnumServiceImpl("page_request_types")
enum class PageRequestTypes(
    val cache: Boolean = true
) : IPageRequestTypes {
    DESCRIPTIONS,
    INFO_COMMANDS,
    INFO_AREA(false),
    INFO_GAME(false),

    EDIT_LIST_CHANGES(false),
    EDIT_LIST_AREA_COMMITS(false)
    ;

    override fun cache(): Boolean = cache
}