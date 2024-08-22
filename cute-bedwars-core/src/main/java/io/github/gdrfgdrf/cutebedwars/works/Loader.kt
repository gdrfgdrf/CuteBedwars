package io.github.gdrfgdrf.cutebedwars.works

import com.github.yitter.contract.IdGeneratorOptions
import com.github.yitter.idgen.YitIdHelper
import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IConfig
import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IConstants
import io.github.gdrfgdrf.cutebedwars.abstracts.core.ILoader
import io.github.gdrfgdrf.cutebedwars.abstracts.database.IDatabase
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IPluginState
import io.github.gdrfgdrf.cutebedwars.abstracts.requests.IRequests
import io.github.gdrfgdrf.cutebedwars.beans.pojo.game.Area
import io.github.gdrfgdrf.cutebedwars.game.managers.Managers
import io.github.gdrfgdrf.cutebedwars.game.managers.area.AreaManager
import io.github.gdrfgdrf.cutebedwars.holders.javaPluginHolder
import io.github.gdrfgdrf.cutebedwars.utils.extension.logError
import io.github.gdrfgdrf.cutebedwars.utils.extension.logInfo
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
        Registry.register(Loader::class.java.classLoader, "io.github.gdrfgdrf.cutebedwars")

        Plugin.state = IPluginState.get("LOADING")

        createFolders()

        CuteFrameworkSupport.load(javaPlugin)
        javaPluginHolder().set(javaPlugin)

        loadConfig()
        loadLanguage()
        loadRequest()
        loadDatabase()

        if (!idGeneratorInitialized) {
            val options = if (IConfig.getWorkerId() != null && IConfig.getWorkerId()!! >= 0) {
                IdGeneratorOptions(IConfig.getWorkerId()!!)
            } else {
                IdGeneratorOptions()
            }

            YitIdHelper.setIdGenerator(options)
            idGeneratorInitialized = true
        }

        loadAreas()
    }

    override fun reloadPhase() {
        createFolders()
        loadConfig()
        loadLanguage()
        loadRequest()
        loadDatabase()
        loadAreas()
    }

    private fun createFolders() {
        val baseFolder = File(IConstants.BASE_FOLDER())
        if (!baseFolder.exists()) {
            baseFolder.mkdirs()
        }
    }

    private fun loadConfig() {
        "Loading the configuration file".logInfo()

        IConfig.set(
            ConfigManager.getInstance().load(
                IConstants.OWNER(),
                IConstants.CONFIG_FILE_NAME(),
                Class.forName("io.github.gdrfgdrf.cutebedwars.commons.Config")
            )
        )
    }

    private fun loadLanguage() {
        "Loading the language".logInfo()

        LanguageLoader.getInstance().load(
            Loader::class.java.classLoader,
            "io.github.gdrfgdrf.cutebedwars.languages.collect",
            "io.github.gdrfgdrf.cutebedwars.languages.language",
            IConstants.OWNER(),
            IConfig.getLanguage(),
        )
    }

    private fun loadRequest() {
        IRequests.get().initialize()
    }

    private fun loadDatabase() {
        IDatabase.get().initialize()
    }

    private fun loadAreas() {
        val folder = File(IConstants.AREA_FOLDER())
        if (!folder.exists()) {
            folder.mkdirs()
        }
        val files = folder.listFiles { _, filename ->
            return@listFiles !filename.endsWith(".json")
        }
        if (files == null) {
            return
        }

        files.forEach {
            runCatching {
                val area = JacksonUtils.readFile<Area>(it, Area::class.java)
                val areaManager = AreaManager(area)

                Managers.register(areaManager)
            }.onFailure {
                "Unable to load area $it".logError(it)
            }
        }
    }
}