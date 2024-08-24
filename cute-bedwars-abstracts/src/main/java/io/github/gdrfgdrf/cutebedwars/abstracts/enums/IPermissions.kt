package io.github.gdrfgdrf.cutebedwars.abstracts.enums

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumService
import org.bukkit.command.CommandSender
import org.bukkit.permissions.Permission

@EnumService("permissions_enum")
interface IPermissions {
    fun name_(): String
    fun groups(): IPermissionGroups
    fun string(): String
    fun get(): String
    fun putToGroup(permissions: Permission)
    fun needOps(): Boolean
    fun hasPermission(sender: CommandSender): Boolean

    companion object {
        fun valueOf(name: String): IPermissions = Mediator.valueOf(IPermissions::class.java, name)!!
        fun values(): Array<*> = Mediator.values(IPermissions::class.java)!!
    }
}