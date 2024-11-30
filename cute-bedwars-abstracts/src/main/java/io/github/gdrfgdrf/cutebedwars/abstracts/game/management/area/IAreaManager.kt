package io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area

import io.github.gdrfgdrf.cutebedwars.abstracts.storage.AbstractAreaCommitStorage
import io.github.gdrfgdrf.cutebedwars.beans.pojo.area.Area
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@Service("area_manager", singleton = false)
interface IAreaManager {
    val area: Area
    val context: IAreaContext?
    val commitStorage: AbstractAreaCommitStorage?
    val initialized: Boolean

    fun init()
    fun save()

    companion object {
        fun new(area: Area): IAreaManager = Mediator.get(IAreaManager::class.java, ArgumentSet(arrayOf(area)))!!
    }
}