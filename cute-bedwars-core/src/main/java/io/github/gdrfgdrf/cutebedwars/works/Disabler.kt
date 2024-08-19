package io.github.gdrfgdrf.cutebedwars.works

import io.github.gdrfgdrf.cutebedwars.database.Database

object Disabler {
    fun disable() {
        Database.getInstance().close()
    }


}