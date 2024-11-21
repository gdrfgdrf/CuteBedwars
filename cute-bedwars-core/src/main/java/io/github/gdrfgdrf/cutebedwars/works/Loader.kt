package io.github.gdrfgdrf.cutebedwars.works

import com.github.yitter.contract.IdGeneratorOptions
import com.github.yitter.idgen.YitIdHelper
import io.github.gdrfgdrf.cutebedwars.abstracts.chatpage.IChatPages
import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IConfig
import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IConstants
import io.github.gdrfgdrf.cutebedwars.abstracts.core.ILoader
import io.github.gdrfgdrf.cutebedwars.abstracts.database.IDatabase
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPluginState
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.IChangeTypeRegistry
import io.github.gdrfgdrf.cutebedwars.abstracts.frequencytasks.IFrequencyTaskManager
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.IManagers
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaManager
import io.github.gdrfgdrf.cutebedwars.abstracts.requests.IRequests
import io.github.gdrfgdrf.cutebedwars.abstracts.tasks.ITaskManager
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logError
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.cutebedwars.beans.pojo.area.Area
import io.github.gdrfgdrf.cuteframework.config.ConfigManager
import io.github.gdrfgdrf.cuteframework.locale.LanguageLoader
import io.github.gdrfgdrf.cuteframework.minecraftplugin.CuteFrameworkSupport
import io.github.gdrfgdrf.cuteframework.utils.jackson.JacksonUtils
import io.github.gdrfgdrf.multimodulemediator.Registry
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

@ServiceImpl("loader")
object Loader : ILoader {
    private var idGeneratorInitialized = false

    fun load(javaPlugin: JavaPlugin) {
        javaPlugin.logger.info("------------------------ CuteBedwars Loading Phase ------------------------")

        runCatching {
            javaPlugin.logger.info("Loading abstract module")
            Registry.register(Loader::class.java.classLoader, "io.github.gdrfgdrf.cutebedwars")

            Plugin.state(IPluginState.valueOf("LOADING"))
            Plugin.javaPlugin(javaPlugin)

            createFolders()

            CuteFrameworkSupport.load(javaPlugin)

            loadConfig()
            loadLanguage()
            loadRequest()
            loadDatabase()
            loadTaskManager()
            loadFrequencyTaskManager()
            loadChatPages()

            if (!idGeneratorInitialized) {
                "Initializing the id generator".logInfo()
                val workerId = IConfig.get<Short>("WorkerId")
                val options = if (workerId >= 0) {
                    "Use the custom worker id: $workerId".logInfo()
                    IdGeneratorOptions(IConfig["WorkerId"])
                } else {
                    "Use the default worker id: $workerId".logInfo()
                    IdGeneratorOptions()
                }

                YitIdHelper.setIdGenerator(options)
                idGeneratorInitialized = true
            }

            loadAreas()
            loadChangeTypeRegistry()
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
        val baseFolder = File(IConstants.baseFolder())
        if (!baseFolder.exists()) {
            baseFolder.mkdirs()
        }
    }

    private fun loadConfig() {
        "Loading the configuration file".logInfo()

        IConfig.set(
            ConfigManager.getInstance().load(
                IConstants.owner(),
                IConstants.configFileName(),
                Class.forName("io.github.gdrfgdrf.cutebedwars.commons.Config")
            )
        )

        IConfig.instance()?.fulfill()
    }

    private fun loadLanguage() {
        "Loading the language".logInfo()

        LanguageLoader.getInstance().load(
            Loader::class.java.classLoader,
            "io.github.gdrfgdrf.cutebedwars.languages.collect",
            "io.github.gdrfgdrf.cutebedwars.languages.language",
            IConstants.owner(),
            IConfig["Language"],
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

    private fun loadAreas() {
        val folder = File(IConstants.areaFolder())
        if (!folder.exists()) {
            folder.mkdirs()
        }
        val files = folder.listFiles { _, filename ->
            return@listFiles !filename.endsWith(".json")
        }
        if (files.isNullOrEmpty()) {
            return
        }

        files.forEach {
            "-------------- Area Loading --------------".logInfo()

            runCatching {
                "Reading a area file: $it".logInfo()

                val area = JacksonUtils.readFile<Area>(it, Area::class.java)
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