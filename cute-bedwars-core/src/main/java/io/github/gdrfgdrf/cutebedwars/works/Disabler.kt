package io.github.gdrfgdrf.cutebedwars.works

import io.github.gdrfgdrf.cutebedwars.commands.manager.SubCommandManager
import io.github.gdrfgdrf.cutebedwars.database.Database
import io.github.gdrfgdrf.cutebedwars.request.Requests

object Disabler {
    fun disable() {
        Database.getInstance().close()
        Requests.reset()
        SubCommandManager.clear()
    }


}