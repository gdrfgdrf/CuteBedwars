package io.github.gdrfgdrf.cutebedwars.abstracts.enums

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumService
import org.bukkit.permissions.Permission
import org.bukkit.permissions.PermissionDefault

@EnumService("permission_groups_enum")
interface IPermissionGroups {
    val prefix: String
    val description: String
    val permissionDefault: PermissionDefault

    fun get(): Permission

    companion object {
        fun valueOf(name: String): IPermissionGroups = Mediator.valueOf(IPermissionGroups::class.java, name)!!
    }
}