package io.github.gdrfgdrf.cutebedwars.notification.base

import io.github.gdrfgdrf.cutebedwars.commons.enums.Permissions

interface PermissibleNotification {
    fun permission(): Permissions?
}