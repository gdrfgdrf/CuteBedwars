package io.github.gdrfgdrf.cutebedwars.abstracts.enums

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumService
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
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
        fun get(name: String): IPermissions = Mediator.get(IPermissions::class.java, ArgumentSet(arrayOf(name)))!!
        fun values(): Array<*> = Mediator.get(IPermissions::class.java)!!
    }
}