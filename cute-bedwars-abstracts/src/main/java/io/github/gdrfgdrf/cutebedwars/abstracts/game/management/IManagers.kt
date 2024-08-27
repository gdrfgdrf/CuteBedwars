package io.github.gdrfgdrf.cutebedwars.abstracts.game.management

import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaManager
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("managers")
@KotlinSingleton
interface IManagers {
    fun register(areaManager: IAreaManager)
    fun unregister(areaManager: IAreaManager)
    fun get(id: Long): IAreaManager?
    fun get(name: String): MutableList<IAreaManager>?
    fun list(): List<IAreaManager>

    fun createArea(name: String): IAreaManager

    companion object {
        fun get(): IManagers = Mediator.get(IManagers::class.java)!!
    }
}