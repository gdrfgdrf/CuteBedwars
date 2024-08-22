package io.github.gdrfgdrf.cutebedwars.abstracts.enums

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumService
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import org.bukkit.permissions.Permission
import org.bukkit.permissions.PermissionDefault

@EnumService("permission_groups_enum")
interface IPermissionGroups {
    fun name_(): String
    fun prefix(): String
    fun description(): String
    fun permissionDefault(): PermissionDefault
    fun get(): Permission

    companion object {
        fun get(name: String): IPermissionGroups = Mediator.get(IPermissionGroups::class.java, ArgumentSet(arrayOf(name)))!!
    }
}