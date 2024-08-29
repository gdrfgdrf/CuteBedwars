package io.github.gdrfgdrf.cutebedwars.game.editing

import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game.IGameContext
import io.github.gdrfgdrf.cutebedwars.game.editing.exception.AlreadyInEditingModeException
import org.bukkit.entity.Player
import java.util.concurrent.ConcurrentHashMap

object Editors {
    private val map = ConcurrentHashMap<String, GameEditor>()

    fun get(uuid: String): GameEditor? {
        return map[uuid]
    }

    fun create(uuid: String, gameContext: IGameContext): GameEditor {
        if (map.containsKey(uuid)) {
            throw AlreadyInEditingModeException()
        }
        return GameEditor(uuid, gameContext)
    }

    fun remove(uuid: String) {
        map.remove(uuid)
    }


}