package io.github.gdrfgdrf.cutebedwars.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IRequestStatuses
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumServiceImpl

@EnumServiceImpl("request_statuses_enum")
enum class RequestStatuses : IRequestStatuses {
    NONE,
    READY,
    TRY_RUNNING,
    WAIT_NEXT_ROUND,
    RUNNING,
    STOPPED;

    override fun name_(): String {
        return name
    }
}