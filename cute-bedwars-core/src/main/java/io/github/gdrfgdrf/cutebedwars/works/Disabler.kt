package io.github.gdrfgdrf.cutebedwars.works

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.SubCommandManager
import io.github.gdrfgdrf.cutebedwars.abstracts.core.Disabler
import io.github.gdrfgdrf.cutebedwars.abstracts.database.Database
import io.github.gdrfgdrf.cutebedwars.abstracts.requests.Requests
import io.github.gdrfgdrf.cutebedwars.commons.utils.thread.ThreadPoolService

object Disabler : Disabler() {
    fun disable() {
        disableDatabase()
        disableRequest()
        disableThreadPool()
        SubCommandManager.get().clear()
    }

    override fun reloadPhase() {
        disableDatabase()
        disableRequest()
        disableThreadPool()
    }

    private fun disableDatabase() {
        Database.get().close()
    }

    private fun disableRequest() {
        Requests.get().reset()
    }

    private fun disableThreadPool() {
        ThreadPoolService.terminate()
    }


}