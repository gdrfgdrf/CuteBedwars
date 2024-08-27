package io.github.gdrfgdrf.cutebedwars.game.management

import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaManager
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.IManagers
import io.github.gdrfgdrf.cutebedwars.beans.pojo.area.Area
import io.github.gdrfgdrf.cutebedwars.utils.extension.logInfo
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import java.util.concurrent.ConcurrentHashMap

@ServiceImpl("managers")
object Managers : IManagers {
    private val nameToAreaManager = ConcurrentHashMap<String, MutableList<IAreaManager>>()
    private val idToAreaManager = ConcurrentHashMap<Long, IAreaManager>()

    override fun register(areaManager: IAreaManager) {
        val area = areaManager.area()
        "Registering an area id: ${area.id}, name: ${area.name}".logInfo()

        val list = nameToAreaManager.computeIfAbsent(area.name) {
            ArrayList()
        }
        list.add(areaManager)
        idToAreaManager[area.id] = areaManager
    }

    override fun unregister(areaManager: IAreaManager) {
        val area = areaManager.area()
        "Unregistering an area id: ${area.id}, name: ${area.name}".logInfo()

        nameToAreaManager.remove(area.name)
        idToAreaManager.remove(area.id)
    }

    override fun get(id: Long): IAreaManager? {
        return idToAreaManager[id]
    }

    override fun get(name: String): MutableList<IAreaManager>? {
        return nameToAreaManager[name]
    }

    override fun list(): List<IAreaManager> {
        if (nameToAreaManager.isEmpty() || idToAreaManager.isEmpty()) {
            return arrayListOf()
        }
        val result = arrayListOf<IAreaManager>()
        idToAreaManager.forEach { (_, areaManager) ->
            result.add(areaManager)
        }
        return result
    }

    override fun createArea(name: String): IAreaManager {
        "Creating an area name: $name".logInfo()

        val area = Area()
        area.name = name
        val manager = IAreaManager.get(area)
        return manager
    }
}