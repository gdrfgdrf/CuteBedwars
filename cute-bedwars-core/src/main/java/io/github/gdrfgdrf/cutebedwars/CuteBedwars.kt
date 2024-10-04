package io.github.gdrfgdrf.cutebedwars

import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.cutebedwars.works.Disabler
import io.github.gdrfgdrf.cutebedwars.works.Enabler
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
        Enabler.enable()

        "CuteBedwars is enabled".logInfo()
    }

    override fun onDisable() {
        super.onDisable()
        Disabler.disable()

        "CuteBedwars is disabled".logInfo()
    }
}