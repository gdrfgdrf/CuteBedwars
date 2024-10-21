package io.github.gdrfgdrf.cutebedwars.works

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.ISubCommandManager
import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IThreadPoolService
import io.github.gdrfgdrf.cutebedwars.abstracts.core.IDisabler
import io.github.gdrfgdrf.cutebedwars.abstracts.core.IPlugin
import io.github.gdrfgdrf.cutebedwars.abstracts.database.IDatabase
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.IChangeTypeRegistry
import io.github.gdrfgdrf.cutebedwars.abstracts.requests.IRequests
import io.github.gdrfgdrf.cutebedwars.abstracts.tasks.ITaskManager
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import org.bukkit.plugin.java.JavaPlugin

@ServiceImpl("disabler")
object Disabler : IDisabler {
    fun disable(javaPlugin: JavaPlugin) {
        javaPlugin.logger.info("------------------------ CuteBedwars Disable Phase ------------------------")

        runCatching {
            disableDatabase()
            disableRequest()
            disableThreadPool()
            disableTaskManager()
            ISubCommandManager.instance().clear()
            disableChangeTypeRegistry()
        }.onFailure {
            javaPlugin.logger.severe("An error occurred while disabling CuteBedwars")
            it.printStackTrace()
        }

        javaPlugin.logger.info("------------------------ CuteBedwars Disable Phase ------------------------")
    }

    override fun reloadPhase() {
        val javaPlugin = IPlugin.instance().javaPlugin()
        val log: (String) -> Unit = {
            if (javaPlugin != null) {
                javaPlugin.logger.info(it)
            } else {
                println(it)
            }
        }

        log("------------------------ CuteBedwars Reloading Phase (Disabler) ------------------------")

        disableDatabase()
        disableRequest()
        disableThreadPool()
        disableTaskManager()
        disableChangeTypeRegistry()

        log("------------------------ CuteBedwars Reloading Phase (Disabler) ------------------------")
    }

    private fun disableDatabase() {
        IDatabase.instance().close()
    }

    private fun disableRequest() {
        IRequests.instance().reset()
    }

    private fun disableThreadPool() {
        IThreadPoolService.instance().terminate()
    }

    private fun disableTaskManager() {
        ITaskManager.instance().terminate()
    }

    private fun disableChangeTypeRegistry() {
        IChangeTypeRegistry.instance().clear()
    }


}