package io.github.gdrfgdrf.cutebedwars.notification.base

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPermissions

interface PermissibleNotification {
    fun permission(): IPermissions?
}