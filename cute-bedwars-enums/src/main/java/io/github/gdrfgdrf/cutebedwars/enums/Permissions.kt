package io.github.gdrfgdrf.cutebedwars.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPermissionGroups
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPermissions
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumServiceImpl
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.permissions.Permission
import org.bukkit.permissions.PermissionDefault

@EnumServiceImpl("permissions_enum")
enum class Permissions(
    override val groups: PermissionGroups,
    override val string: String,
    override val needOps: Boolean = groups == PermissionGroups.ADMIN
): IPermissions {
    ROOT(PermissionGroups.USER, "root"),

    DEV(PermissionGroups.ADMIN, "dev"),

    HELP(PermissionGroups.USER, "help"),
    RELOAD(PermissionGroups.ADMIN, "reload"),
    QUERY_DESCRIPTION(PermissionGroups.USER, "query.description"),
    QUERY_ADMINISTRATION_DESCRIPTION(PermissionGroups.ADMIN, "description.query_administration"),

    INFO_COMMANDS(PermissionGroups.USER, "info.commands"),
    INFO_ADMINISTRATION_COMMANDS(PermissionGroups.ADMIN, "info.administration_commands"),


    CREATE_AREA(PermissionGroups.ADMIN, "create.area"),
    INFO_AREA(PermissionGroups.ADMIN, "info.area"),
    EDITOR_AREA(PermissionGroups.ADMIN, "editor.area"),

    CREATE_GAME(PermissionGroups.ADMIN, "create.game"),
    INFO_GAME(PermissionGroups.ADMIN, "info.game"),
    EDITOR_GAME(PermissionGroups.ADMIN, "editor.game"),

    EDIT_NEW_CHANGES(PermissionGroups.ADMIN, "edit.new_changes"),
    EDIT_MAKE(PermissionGroups.ADMIN, "edit.make"),
    EDIT_LIST_CHANGES(PermissionGroups.ADMIN, "edit.list_changes"),
    EDIT_COMMIT(PermissionGroups.ADMIN, "edit.commit"),
    EDIT_REVERT_COMMIT(PermissionGroups.ADMIN, "edit.revert_commit"),
    EDIT_EXIT(PermissionGroups.ADMIN, "edit.exit"),

    EDIT_LIST_AREA_COMMITS(PermissionGroups.ADMIN, "edit.list_area_commits"),
    EDIT_LIST_GAME_COMMITS(PermissionGroups.ADMIN, "edit.list_game_commits"),


    RECEIVE_NOTIFICATION(PermissionGroups.USER, "receive.notification"),
    RECEIVE_ADMINISTRATION_NOTIFICATION(PermissionGroups.ADMIN, "receive.administration_notification")

    ;

    override fun get(): String {
        return COMMON_PREFIX + groups.prefix + string
    }

    override fun putToGroup(permissions: Permission) {
        val full = get()
        permissions.children[full] = true
    }

    override fun hasPermission(sender: CommandSender): Boolean {
        if (sender is ConsoleCommandSender) {
            return true
        }
        return sender.hasPermission(get())
    }

    companion object {
        const val COMMON_PREFIX = "cutebedwars."
    }
}