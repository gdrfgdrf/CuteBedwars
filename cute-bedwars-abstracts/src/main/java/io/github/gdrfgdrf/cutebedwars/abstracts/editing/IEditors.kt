package io.github.gdrfgdrf.cutebedwars.abstracts.editing

import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaContext
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game.IGameContext
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("editors")
@KotlinSingleton
interface IEditors {
    fun get(uuid: String): AbstractEditor<*>?
    fun createAreaEditor(uuid: String, areaContext: IAreaContext): AbstractAreaEditor
    fun createGameEditor(uuid: String, gameContext: IGameContext): AbstractGameEditor
    fun put(abstractEditor: AbstractEditor<*>)
    fun remove(uuid: String)

    companion object {
        fun get(): IEditors = Mediator.get(IEditors::class.java)!!
    }
}