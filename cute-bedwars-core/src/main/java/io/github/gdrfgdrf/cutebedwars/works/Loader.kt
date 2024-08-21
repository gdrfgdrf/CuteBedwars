package io.github.gdrfgdrf.cutebedwars.works

import io.github.gdrfgdrf.cutebedwars.abstracts.database.Database
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.PluginState
import io.github.gdrfgdrf.cutebedwars.abstracts.requests.Requests
import io.github.gdrfgdrf.cutebedwars.commons.Config
import io.github.gdrfgdrf.cutebedwars.commons.Constants
import io.github.gdrfgdrf.cutebedwars.commons.extension.logInfo
import io.github.gdrfgdrf.cutebedwars.holders.javaPluginHolder
import io.github.gdrfgdrf.cuteframework.config.ConfigManager
import io.github.gdrfgdrf.cuteframework.locale.LanguageLoader
import io.github.gdrfgdrf.cuteframework.minecraftplugin.CuteFrameworkSupport
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

object Loader : io.github.gdrfgdrf.cutebedwars.abstracts.core.Loader() {
    fun load(javaPlugin: JavaPlugin) {
        Plugin.state = PluginState.LOADING

        createFolders()

        CuteFrameworkSupport.load(javaPlugin)
        javaPluginHolder().set(javaPlugin)

        loadConfig()
        loadLanguage()
        loadRequest()
        loadDatabase()
    }

    override fun reloadPhase() {
        createFolders()
        loadConfig()
        loadLanguage()
        loadRequest()
        loadDatabase()
    }

    private fun createFolders() {
        val baseFolder = File(Constants.BASE_FOLDER)
        if (!baseFolder.exists()) {
            baseFolder.mkdirs()
        }
    }

    private fun loadConfig() {
        "Loading the configuration file".logInfo()

        Config.INSTANCE = ConfigManager.getInstance().load(
            Constants.OWNER,
            Constants.CONFIG_FILE_NAME,
            Config::class.java
        )
    }

    private fun loadLanguage() {
        "Loading the language".logInfo()

        LanguageLoader.getInstance().load(
            Loader::class.java.classLoader,
            "io.github.gdrfgdrf.cutebedwars.locale.collect",
            "io.github.gdrfgdrf.cutebedwars.locale.language",
            Constants.OWNER,
            Config.INSTANCE.language,
        )
    }

    private fun loadRequest() {
        Requests.get().initialize()
    }

    private fun loadDatabase() {
        Database.get().initialize()
    }
}