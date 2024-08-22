package io.github.gdrfgdrf.cutebedwars.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPermissionGroups
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumServiceImpl
import org.bukkit.permissions.Permission
import org.bukkit.permissions.PermissionDefault

@EnumServiceImpl("permission_groups_enum")
enum class PermissionGroups(
    val prefix: String,
    val description: String,
    val permissionDefault: PermissionDefault
): IPermissionGroups {
    USER(
        "user.",
        "Provides access to basic commands",
        PermissionDefault.TRUE
    ),
    ADMIN(
        "admin.",
        "Provides access to all commands",
        PermissionDefault.OP
    )
    ;

    override fun name_(): String {
        return name
    }

    override fun prefix(): String {
        return prefix
    }

    override fun description(): String {
        return description
    }

    override fun permissionDefault(): PermissionDefault {
        return permissionDefault
    }

    override fun get(): Permission {
        return Permission(
            "${Permissions.COMMON_PREFIX}$prefix*",
            description,
            permissionDefault
        )
    }
}