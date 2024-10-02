package io.github.gdrfgdrf.cutebedwars.works

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.ISubCommandManager
import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IThreadPoolService
import io.github.gdrfgdrf.cutebedwars.abstracts.core.IDisabler
import io.github.gdrfgdrf.cutebedwars.abstracts.database.IDatabase
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.IChangeTypeRegistry
import io.github.gdrfgdrf.cutebedwars.abstracts.requests.IRequests
import io.github.gdrfgdrf.cutebedwars.abstracts.tasks.ITaskManager
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl

@ServiceImpl("disabler")
object Disabler : IDisabler {
    private var threadPoolService: IThreadPoolService? = null
        get() {
            if (field == null) {
                field = Mediator.get<IThreadPoolService>(IThreadPoolService::class.java)!!
            }
            return field
        }

    fun disable() {
        disableDatabase()
        disableRequest()
        disableThreadPool()
        disableTaskManager()
        ISubCommandManager.instance().clear()
        disableChangeTypeRegistry()
    }

    override fun reloadPhase() {
        disableDatabase()
        disableRequest()
        disableThreadPool()
        disableChangeTypeRegistry()
    }

    private fun disableDatabase() {
        IDatabase.instance().close()
    }

    private fun disableRequest() {
        IRequests.instance().reset()
    }

    private fun disableThreadPool() {
        threadPoolService?.terminate()
    }

    private fun disableTaskManager() {
        ITaskManager.instance().terminate()
    }

    private fun disableChangeTypeRegistry() {
        IChangeTypeRegistry.instance().clear()
    }


}