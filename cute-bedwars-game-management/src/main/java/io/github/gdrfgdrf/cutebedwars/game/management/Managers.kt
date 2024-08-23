package io.github.gdrfgdrf.cutebedwars.game.management

import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.IAreaManager
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.IManagers
import io.github.gdrfgdrf.cutebedwars.game.management.area.AreaManager
import io.github.gdrfgdrf.cutebedwars.utils.extension.logInfo
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import java.util.concurrent.ConcurrentHashMap

@ServiceImpl("managers")
object Managers : IManagers{
    private val nameToAreaManager = ConcurrentHashMap<String, IAreaManager>()
    private val idToAreaManager = ConcurrentHashMap<Long, IAreaManager>()

    override fun register(areaManager: IAreaManager) {
        val area = areaManager.area()
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

    override fun get(name: String): IAreaManager? {
        return nameToAreaManager[name]
    }

    override fun get(id: Long): IAreaManager? {
        return idToAreaManager[id]
    }
}