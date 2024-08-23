package io.github.gdrfgdrf.cutebedwars.abstracts.game.management

import io.github.gdrfgdrf.cutebedwars.beans.pojo.game.Area
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("area_manager", singleton = false)
interface IAreaManager {
    fun save()
    fun area(): Area

    companion object {
        fun get(area: Area): IAreaManager = Mediator.get(IAreaManager::class.java)!!
    }
}