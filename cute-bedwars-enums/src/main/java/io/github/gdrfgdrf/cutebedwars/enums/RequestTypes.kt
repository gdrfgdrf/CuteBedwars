package io.github.gdrfgdrf.cutebedwars.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IRequestTypes
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumServiceImpl

@EnumServiceImpl("request_type_enum")
enum class RequestTypes(private val displayName: String): IRequestTypes {
    RELOAD("reload"),
    CREATE_AREA("create_area"),
    CREATE_GAME("create_game"),
    EDIT_NEW_CHANGES("edit_new_changes")
    ;

    override fun name_(): String {
        return name
    }

    override fun displayName(): String {
        return displayName
    }
}