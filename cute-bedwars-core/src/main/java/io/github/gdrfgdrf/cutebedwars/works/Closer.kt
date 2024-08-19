package io.github.gdrfgdrf.cutebedwars.works

import io.github.gdrfgdrf.cutebedwars.database.Database

object Closer {
    fun close() {
        Database.getInstance().close()
    }


}