package io.github.gdrfgdrf.cutebedwars.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPermissionGroups
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPermissions
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumServiceImpl
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.permissions.Permission

@EnumServiceImpl("permissions_enum")
enum class Permissions(
    val groups: PermissionGroups,
    val string: String
): IPermissions {
    ROOT(PermissionGroups.USER, "root"),
    HELP(PermissionGroups.USER, "help"),
    RELOAD(PermissionGroups.USER, "reload"),
    QUERY_DESCRIPTION(PermissionGroups.USER, "description.query"),
    QUERY_ADMINISTRATION_DESCRIPTION(PermissionGroups.ADMIN, "description.query_administration"),


    CREATE_AREA(PermissionGroups.ADMIN, "create.area"),
    INFO_AREA(PermissionGroups.ADMIN, "info.area"),
    MODIFY_AREA(PermissionGroups.ADMIN, "modify.area"),


    RECEIVE_NOTIFICATION(PermissionGroups.USER, "receive_notification"),
    RECEIVE_ADMINISTRATION_NOTIFICATION(PermissionGroups.ADMIN, "receive_administration_notification")

    ;

    override fun name_(): String {
        return name
    }

    override fun groups(): IPermissionGroups {
        return groups
    }

    override fun string(): String {
        return string
    }

    override fun get(): String {
        return COMMON_PREFIX + groups.prefix + string
    }

    override fun putToGroup(permissions: Permission) {
        val full = get()
        permissions.children[full] = true
    }

    override fun needOps(): Boolean {
        return groups == PermissionGroups.ADMIN
    }

    override fun hasPermission(sender: CommandSender): Boolean {
        if (sender is ConsoleCommandSender) {
            return true
        }
        return sender.hasPermission(get())
    }

    companion object {
        val COMMON_PREFIX = "cutebedwars."
    }
}