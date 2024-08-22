package io.github.gdrfgdrf.cutebedwars.notification.base

import org.bukkit.command.CommandSender

abstract class AbstractNotification {
    abstract fun notify(prefix: String? = null, sender: CommandSender)
}