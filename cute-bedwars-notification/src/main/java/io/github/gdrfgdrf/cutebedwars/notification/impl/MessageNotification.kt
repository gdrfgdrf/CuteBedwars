package io.github.gdrfgdrf.cutebedwars.notification.impl

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPermissions
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILocalizationContext
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILocalizationMessage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.cutebedwars.notification.base.AbstractNotification
import io.github.gdrfgdrf.cutebedwars.notification.base.PermissibleNotification
import org.bukkit.command.CommandSender

class MessageNotification(val messageGetter: ILocalizationContext.() -> Array<ILocalizationMessage>) : AbstractNotification(),
    PermissibleNotification {
    var permission: IPermissions? = null

    override fun notify(prefix: String?, sender: CommandSender) {
        localizationScope(sender) {
            if (prefix != null) {
                messageGetter(this).forEach {
                    it.send(prefix)
                }
            } else {
                messageGetter(this).forEach {
                    it.send()
                }
            }
        }
    }

    override fun permission(): IPermissions? = permission
}