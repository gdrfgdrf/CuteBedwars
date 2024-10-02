package io.github.gdrfgdrf.cutebedwars.abstracts.editing

import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaContext

abstract class AbstractAreaEditor(uuid: String, private val areaContext: IAreaContext) : AbstractEditor<IAreaContext>(uuid, areaContext) {
    fun save(allFinished: () -> Unit) {
        val commitStorage = areaContext.manager().commitStorage()

        commits().forEach {
            commitStorage.save(it) {}
            allFinished()
        }
    }
}