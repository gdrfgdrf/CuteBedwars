package io.github.gdrfgdrf.cutebedwars.notification

import io.github.gdrfgdrf.cutebedwars.commons.enums.Permissions
import io.github.gdrfgdrf.cutebedwars.locale.LocalizationContext
import io.github.gdrfgdrf.cutebedwars.locale.LocalizationMessage
import io.github.gdrfgdrf.cutebedwars.locale.collect.NotificationLanguage
import io.github.gdrfgdrf.cutebedwars.locale.extension.middleWork
import io.github.gdrfgdrf.cutebedwars.notification.base.AbstractNotification
import io.github.gdrfgdrf.cutebedwars.notification.base.PermissibleNotification
import io.github.gdrfgdrf.cutebedwars.notification.impl.MessageNotification
import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

object Notifications {
    private val offlineNotifications = ConcurrentHashMap<UUID, MutableList<AbstractNotification>>()

    fun messageAdministrator(messageGetter: LocalizationContext.() -> Array<LocalizationMessage>) {
        val notification = MessageNotification(messageGetter)
        notification.permission = Permissions.RECEIVE_ADMINISTRATION_NOTIFICATION

        Bukkit.getServer().onlinePlayers.forEach {
            if (Permissions.RECEIVE_ADMINISTRATION_NOTIFICATION.hasPermission(it)) {
                notification.notify(sender = it)
            }
        }

        Bukkit.getServer().offlinePlayers.forEach {
            if (Permissions.RECEIVE_ADMINISTRATION_NOTIFICATION.hasPermission(it.player)) {
                putOffline(it, notification)
            }
        }

        val consoleSender = Bukkit.getConsoleSender()
        notification.notify(sender = consoleSender)
    }

    private fun putOffline(offlinePlayer: OfflinePlayer, notification: AbstractNotification) {
        val notifications =
            offlineNotifications.computeIfAbsent(offlinePlayer.uniqueId) { _ -> ArrayList() }
        notifications.add(notification)
    }

    fun notifyOffline(player: Player) {
        if (!offlineNotifications.containsKey(player.uniqueId)) {
            return
        }
        val notifications = offlineNotifications[player.uniqueId]
        if (notifications.isNullOrEmpty()) {
            return
        }

        val filtered = notifications.stream()
            .filter {
                if (it !is PermissibleNotification || it.permission() == null) {
                    return@filter true
                }
                return@filter it.permission()!!.hasPermission(player)
            }
            .toList()
        if (filtered.isNullOrEmpty()) {
            return
        }

        middleWork(commandSender = player) {
            message(NotificationLanguage.OFFLINE_MESSAGE)
                .send("")

            filtered.forEach {
                it.notify("", player)
            }
        }

        offlineNotifications.remove(player.uniqueId)
    }
}