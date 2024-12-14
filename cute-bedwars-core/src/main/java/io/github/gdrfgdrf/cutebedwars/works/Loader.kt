package io.github.gdrfgdrf.cutebedwars.works

import com.github.yitter.contract.IdGeneratorOptions
import com.github.yitter.idgen.YitIdHelper
import io.github.gdrfgdrf.cutebedwars.abstracts.chatpage.IChatPages
import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IIConfig
import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IConstants
import io.github.gdrfgdrf.cutebedwars.abstracts.core.ILoader
import io.github.gdrfgdrf.cutebedwars.abstracts.core.IPlugin
import io.github.gdrfgdrf.cutebedwars.abstracts.database.IDatabase
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPluginState
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.IChangeTypeRegistry
import io.github.gdrfgdrf.cutebedwars.abstracts.frequencytasks.IFrequencyTaskManager
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.IManagers
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaManager
import io.github.gdrfgdrf.cutebedwars.abstracts.items.IItemCollections
import io.github.gdrfgdrf.cutebedwars.abstracts.particles.IParticles
import io.github.gdrfgdrf.cutebedwars.abstracts.requests.IRequests
import io.github.gdrfgdrf.cutebedwars.abstracts.tasks.ITaskManager
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.*
import io.github.gdrfgdrf.cutebedwars.beans.pojo.area.Area
import io.github.gdrfgdrf.multimodulemediator.Registry
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import java.io.File

@ServiceImpl("loader")
object Loader : ILoader {
    private var idGeneratorInitialized = false

    fun load(javaPlugin: JavaPlugin) {
        javaPlugin.logger.info("------------------------ CuteBedwars Loading Phase ------------------------")

        runCatching {
            javaPlugin.logger.info("Loading abstract module")
            Registry.register(Loader::class.java.classLoader, "io.github.gdrfgdrf.cutebedwars")

            Plugin.state = IPluginState.valueOf("LOADING")
            Plugin.javaPlugin = javaPlugin

            createFolders()

            loadConfig()
            loadLanguage()
            loadRequest()
            loadDatabase()
            loadTaskManager()
            loadFrequencyTaskManager()
            loadChatPages()
            loadParticles()

            if (!idGeneratorInitialized) {
                "Initializing the id generator".logInfo()
                val workerId = IIConfig.get<Short>("WorkerId")
                val options = if (workerId.toInt() != 0) {
                    "Use the custom worker id: $workerId".logInfo()
                    IdGeneratorOptions(IIConfig["WorkerId"])
                } else {
                    "Use the default worker id: $workerId".logInfo()
                    IdGeneratorOptions()
                }

                YitIdHelper.setIdGenerator(options)
                idGeneratorInitialized = true
            }

            loadAreas()
            loadChangeTypeRegistry()

            if (IIConfig["EnableDebugLogging"]) {
                "Debug logging is enabled".logDebug()
            }
        }.onFailure {
            javaPlugin.logger.severe("An error occurred while loading CuteBedwars")
            it.printStackTrace()
        }

        javaPlugin.logger.info("------------------------ CuteBedwars Loading Phase ------------------------")
    }

    override fun reloadPhase() {
        "------------------------ CuteBedwars Reloading Phase (Loader) ------------------------".logInfo()

        createFolders()
        loadConfig()
        loadLanguage()
        loadRequest()
        loadDatabase()
        loadTaskManager()
        loadFrequencyTaskManager()
        loadChatPages()
        loadAreas()
        loadChangeTypeRegistry()

        "------------------------ CuteBedwars Reloading Phase (Loader) ------------------------".logInfo()
    }

    private fun createFolders() {
        val baseFolder = File(IConstants["BASE_FOLDER"])
        if (!baseFolder.exists()) {
            baseFolder.mkdirs()
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun loadConfig() {
        "Loading the configuration file".logInfo()

        val config = IConfigs.instance().load(
            IConstants["CONFIG_FILE_NAME"],
            Class.forName("io.github.gdrfgdrf.cutebedwars.commons.Config") as Class<IIConfig>
        )
        IIConfig.set(config)

        IIConfig.instance()?.fulfill()
    }

    private fun loadLanguage() {
        "Loading the language".logInfo()

        ILocales.instance().load(
            Loader::class.java.classLoader,
            "io.github.gdrfgdrf.cutebedwars.languages.collect",
            "io.github.gdrfgdrf.cutebedwars.languages.language",
            IIConfig["Language"],
        )
    }

    private fun loadRequest() {
        IRequests.instance().initialize()
    }

    private fun loadDatabase() {
        IDatabase.instance().initialize()
    }

    private fun loadTaskManager() {
        ITaskManager.instance().start()
    }

    private fun loadFrequencyTaskManager() {
        IFrequencyTaskManager.instance().start()
    }

    private fun loadChatPages() {
        IChatPages.instance().initialize()
    }

    private fun loadParticles() {
        IParticles.instance().initialize()
    }

    private fun loadAreas() {
        IManagers.instance().merge().forEach {
            IManagers.instance().unregister(it)
        }

        val folder = File(IConstants["AREA_FOLDER"])
        if (!folder.exists()) {
            folder.mkdirs()
        }
        val areaFolders = folder.listFiles { dir, _ ->
            return@listFiles dir.isDirectory
        }
        if (areaFolders.isNullOrEmpty()) {
            return
        }

        areaFolders.forEach { areaFolder ->
            "-------------- Area Loading --------------".logInfo()

            runCatching {
                val file = File(areaFolder, "area.json")
                "Reading a area file: $file".logInfo()

                val area = IJsons.instance().read<Area>(file, Area::class.java)
                "The area file is read id: ${area.id}, name: ${area.name}".logInfo()

                "Creating the area manager".logInfo()
                val areaManager = IAreaManager.new(area)

                "Registering a area manager".logInfo()
                IManagers.instance().register(areaManager)
            }.onFailure {
                "Unable to load area $it".logError(it)
            }

            "-------------- Area Loading --------------".logInfo()
        }
    }

    private fun loadChangeTypeRegistry() {
        IChangeTypeRegistry.instance().init()
    }
}