package io.github.gdrfgdrf.cutebedwars

import io.github.gdrfgdrf.cutebedwars.commons.extension.logInfo
import io.github.gdrfgdrf.cutebedwars.listener.PlayerJoinListener
import io.github.gdrfgdrf.cutebedwars.works.Closer
import io.github.gdrfgdrf.cutebedwars.works.Loader
import org.bukkit.plugin.java.JavaPlugin

class CuteBedwars : JavaPlugin() {
    override fun onLoad() {
        super.onLoad()
        Loader.load(this)

        "CuteBedwars is loaded".logInfo()
    }

    override fun onEnable() {
        super.onEnable()

        server.pluginManager.registerEvents(PlayerJoinListener, this)

        "CuteBedwars is enabled".logInfo()
    }

    override fun onDisable() {
        super.onDisable()
        Closer.close()

        "CuteBedwars is disabled".logInfo()
    }
}