package io.github.gdrfgdrf.cutebedwars.works

import io.github.gdrfgdrf.cutebedwars.commands.manager.SubCommandManager
import io.github.gdrfgdrf.cutebedwars.database.Database

object Disabler {
    fun disable() {
        Database.getInstance().close()
        SubCommandManager.clear()
    }


}