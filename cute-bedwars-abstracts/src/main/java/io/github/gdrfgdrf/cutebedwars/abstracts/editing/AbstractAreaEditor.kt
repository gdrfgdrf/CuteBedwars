package io.github.gdrfgdrf.cutebedwars.abstracts.editing

import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaContext
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo

abstract class AbstractAreaEditor(uuid: String, private val areaContext: IAreaContext) : AbstractEditor<IAreaContext>(uuid, areaContext) {
    @Synchronized
    fun save(allFinished: () -> Unit) {
        "Saving commits of an area editor".logInfo()
        val commitStorage = areaContext.manager.commitStorage ?: return

        commits().forEach {
            commitStorage.save(it) {}
            allFinished()
        }

        clear()
    }
}