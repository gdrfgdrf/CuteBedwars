package io.github.gdrfgdrf.cutebedwars.game.management

import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.IAreaManager
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.IManagers
import io.github.gdrfgdrf.cutebedwars.beans.pojo.game.Area
import io.github.gdrfgdrf.cutebedwars.utils.extension.logInfo
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import java.util.concurrent.ConcurrentHashMap

@ServiceImpl("managers")
object Managers : IManagers {
    private val nameToAreaManager = ConcurrentHashMap<String, IAreaManager>()
    private val idToAreaManager = ConcurrentHashMap<Long, IAreaManager>()

    override fun register(areaManager: IAreaManager) {
        val area = areaManager.area()
        "Registering an area id: ${area.id}, name: ${area.name}".logInfo()

        nameToAreaManager[area.name] = areaManager
        idToAreaManager[area.id] = areaManager
    }

    override fun unregister(areaManager: IAreaManager) {
        val area = areaManager.area()
        "Unregistering an area id: ${area.id}, name: ${area.name}".logInfo()

        nameToAreaManager.remove(area.name)
        idToAreaManager.remove(area.id)
    }

    override fun get(name: String): IAreaManager? {
        return nameToAreaManager[name]
    }

    override fun get(id: Long): IAreaManager? {
        return idToAreaManager[id]
    }

    override fun createArea(name: String): IAreaManager {
        "Creating an area name: $name".logInfo()

        val area = Area()
        area.name = name
        val manager = IAreaManager.get(area)

        register(manager)
        return manager
    }
}