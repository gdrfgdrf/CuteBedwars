package io.github.gdrfgdrf.cutebedwars.game.managers

import io.github.gdrfgdrf.cutebedwars.commons.extension.logInfo
import io.github.gdrfgdrf.cutebedwars.game.managers.area.AreaManager
import java.util.concurrent.ConcurrentHashMap

object Managers {
    private val nameToAreaManager = ConcurrentHashMap<String, AreaManager>()
    private val idToAreaManager = ConcurrentHashMap<Long, AreaManager>()

    fun register(areaManager: AreaManager) {
        val area = areaManager.area
        "Registering an area id: ${area.id}, name: ${area.name}".logInfo()


        nameToAreaManager[area.name] = areaManager
        idToAreaManager[area.id] = areaManager
    }

    fun unregister(areaManager: AreaManager) {
        val area = areaManager.area
        "Unregistering an area id: ${area.id}, name: ${area.name}".logInfo()

        nameToAreaManager.remove(area.name)
        idToAreaManager.remove(area.id)
    }

    fun get(name: String): AreaManager? {
        return nameToAreaManager[name]
    }

    fun get(id: Long): AreaManager? {
        return idToAreaManager[id]
    }
}