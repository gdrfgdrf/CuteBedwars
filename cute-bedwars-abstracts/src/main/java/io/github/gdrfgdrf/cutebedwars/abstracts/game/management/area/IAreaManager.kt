package io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area

import io.github.gdrfgdrf.cutebedwars.beans.pojo.area.Area
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@Service("area_manager", singleton = false)
interface IAreaManager {
    fun save()
    fun area(): Area
    fun context(): IAreaContext

    companion object {
        fun get(area: Area): IAreaManager = Mediator.get(IAreaManager::class.java, ArgumentSet(arrayOf(area)))!!
    }
}