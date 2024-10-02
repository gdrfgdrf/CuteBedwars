package io.github.gdrfgdrf.cutebedwars.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPermissionGroups
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumServiceImpl
import org.bukkit.permissions.Permission
import org.bukkit.permissions.PermissionDefault

@EnumServiceImpl("permission_groups_enum")
enum class PermissionGroups(
    val prefix: String,
    private val description: String,
    private val permissionDefault: PermissionDefault
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

    override fun prefix(): String = prefix
    override fun description(): String = description
    override fun permissionDefault(): PermissionDefault = permissionDefault

    override fun get(): Permission {
        return Permission(
            "${Permissions.COMMON_PREFIX}$prefix*",
            description,
            permissionDefault
        )
    }
}