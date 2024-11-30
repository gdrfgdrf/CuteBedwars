package io.github.gdrfgdrf.cutebedwars.abstracts.enums

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumService
import org.bukkit.command.CommandSender
import org.bukkit.permissions.Permission

@EnumService("permissions_enum")
interface IPermissions {
    val groups: IPermissionGroups
    val string: String
    val needOps: Boolean

    fun get(): String
    fun putToGroup(permissions: Permission)
    fun hasPermission(sender: CommandSender): Boolean

    companion object {
        fun valueOf(name: String): IPermissions = Mediator.valueOf(IPermissions::class.java, name)!!
        fun values(): Array<*> = Mediator.values(IPermissions::class.java)!!
    }
}