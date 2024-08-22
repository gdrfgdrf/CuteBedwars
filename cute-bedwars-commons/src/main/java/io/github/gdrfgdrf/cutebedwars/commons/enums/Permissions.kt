package io.github.gdrfgdrf.cutebedwars.commons.enums

import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.permissions.Permission
import org.bukkit.permissions.PermissionDefault

enum class Permissions(
    val groups: Groups,
    val string: String
) {
    ROOT(Groups.USER, "root"),
    HELP(Groups.USER, "help"),
    RELOAD(Groups.USER, "reload"),
    RECEIVE_NOTIFICATION(Groups.USER, "receive_notification"),
    RECEIVE_ADMINISTRATION_NOTIFICATION(Groups.ADMIN, "receive_administration_notification")

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

    fun hasPermission(sender: CommandSender): Boolean {
        if (sender is ConsoleCommandSender) {
            return true
        }
        return sender.hasPermission(get())
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