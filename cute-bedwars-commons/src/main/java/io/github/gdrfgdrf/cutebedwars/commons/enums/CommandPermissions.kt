package io.github.gdrfgdrf.cutebedwars.commons.enums

import org.bukkit.permissions.Permission
import org.bukkit.permissions.PermissionDefault

enum class CommandPermissions(
    val groups: Groups,
    val string: String
) {
    ROOT(Groups.USER, "root"),
    HELP(Groups.USER, "help"),
    RELOAD(Groups.USER, "reload")

    ;

    fun get(): String {
        return COMMON_PREFIX + groups.prefix + string
    }

    fun putToGroup(permission: Permission) {
        val full = get()
        permission.children[full] = true
    }

    fun needOps(): Boolean {
        return groups == Groups.ADMIN
    }

    enum class Groups(
        val prefix: String,
        val description: String,
        val permissionDefault: PermissionDefault
    ) {
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

        fun get(): Permission {
            return Permission(
                "$COMMON_PREFIX$prefix*",
                description,
                permissionDefault
            )
        }
    }

    companion object {
        val COMMON_PREFIX = "cutebedwars."
    }
}