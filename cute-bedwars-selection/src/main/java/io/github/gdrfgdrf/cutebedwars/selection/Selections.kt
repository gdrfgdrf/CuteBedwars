package io.github.gdrfgdrf.cutebedwars.selection

import io.github.gdrfgdrf.cutebedwars.abstracts.selection.ISelect
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import org.bukkit.entity.Player
import java.util.concurrent.ConcurrentHashMap

@ServiceImpl("selections")
object Selections {
    private val map = ConcurrentHashMap<Player, ISelect>()

    fun get(player: Player): ISelect? {
        return map[player]
    }

    fun create(player: Player): ISelect {
        if (map.containsKey(player)) {
            val select = map[player]
            select!!.destroy()
            map.remove(player)
        }

        val select = Select(player)
        map[player] = select

        return select
    }

}