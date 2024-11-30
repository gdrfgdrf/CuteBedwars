package io.github.gdrfgdrf.cutebedwars.game.editing

import io.github.gdrfgdrf.cutebedwars.abstracts.editing.AbstractAreaEditor
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.AbstractEditor
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.AbstractGameEditor
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.IEditors
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaContext
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game.IGameContext
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.cutebedwars.game.editing.editor.AreaEditor
import io.github.gdrfgdrf.cutebedwars.game.editing.editor.GameEditor
import io.github.gdrfgdrf.cutebedwars.game.editing.exception.AlreadyInEditingModeException
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import java.util.concurrent.ConcurrentHashMap

@ServiceImpl("editors")
object Editors : IEditors {
    private val map = ConcurrentHashMap<String, AbstractEditor<*>>()

    override fun get(uuid: String): AbstractEditor<*>? {
        return map[uuid]
    }

    override fun createAreaEditor(uuid: String, areaContext: IAreaContext): AbstractAreaEditor {
        "Creating area editor, uuid: $uuid, area's id: ${areaContext.manager().area.id}".logInfo()
        return AreaEditor(uuid, areaContext)
    }

    override fun createGameEditor(uuid: String, gameContext: IGameContext): AbstractGameEditor {
        "Creating game editor, uuid: $uuid, game's id: ${gameContext.game().id}, area's id: ${gameContext.game().areaId}".logInfo()
        return GameEditor(uuid, gameContext)
    }

    override fun put(abstractEditor: AbstractEditor<*>) {
        if (map.containsKey(abstractEditor.uuid)) {
            throw AlreadyInEditingModeException()
        }
        map[abstractEditor.uuid] = abstractEditor
    }

    override fun remove(uuid: String) {
        map.remove(uuid)
    }


}