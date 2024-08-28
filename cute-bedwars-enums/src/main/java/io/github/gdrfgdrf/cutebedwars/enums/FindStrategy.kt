package io.github.gdrfgdrf.cutebedwars.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IFindStrategy
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumServiceImpl

@EnumServiceImpl("find_strategy")
enum class FindStrategy : IFindStrategy {
    ONLY_ONE,
    NOTICE_WHEN_MULTIPLE_RESULT
}