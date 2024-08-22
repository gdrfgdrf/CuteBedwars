package io.github.gdrfgdrf.cutebedwars.notification.impl

import io.github.gdrfgdrf.cutebedwars.commons.enums.Permissions
import io.github.gdrfgdrf.cutebedwars.locale.LocalizationContext
import io.github.gdrfgdrf.cutebedwars.locale.LocalizationMessage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.cutebedwars.notification.base.AbstractNotification
import io.github.gdrfgdrf.cutebedwars.notification.base.PermissibleNotification
import org.bukkit.command.CommandSender

class MessageNotification(val messageGetter: LocalizationContext.() -> Array<LocalizationMessage>) : AbstractNotification(),
    PermissibleNotification {
    var permission: Permissions? = null

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

    override fun permission(): Permissions? = permission
}