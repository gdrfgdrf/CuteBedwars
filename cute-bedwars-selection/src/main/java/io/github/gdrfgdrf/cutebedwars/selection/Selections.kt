package io.github.gdrfgdrf.cutebedwars.selection

import io.github.gdrfgdrf.cutebedwars.abstracts.selection.ISelect
import io.github.gdrfgdrf.cutebedwars.abstracts.selection.ISelections
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.uuid
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import org.bukkit.entity.Player
import java.util.concurrent.ConcurrentHashMap

@ServiceImpl("selections")
object Selections : ISelections {
    private val map = ConcurrentHashMap<String, ISelect>()

    override fun get(player: Player): ISelect? {
        return map[player.uuid()]
    }

    override fun has(player: Player): Boolean {
        return get(player) != null
    }

    override fun create(player: Player): ISelect {
        remove(player)

        val select = Select(player)
        map[player.uuid()] = select

        return select
    }

    override fun remove(player: Player) {
        if (map.containsKey(player.uuid())) {
            val select = map[player.uuid()]
            select!!.destroy()
            map.remove(player.uuid())
        }
    }
}