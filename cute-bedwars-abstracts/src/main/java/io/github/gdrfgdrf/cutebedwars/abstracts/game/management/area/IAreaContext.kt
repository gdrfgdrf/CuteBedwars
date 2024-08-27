package io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area

import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.ISetter
import io.github.gdrfgdrf.cutebedwars.beans.pojo.game.Game
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import org.bukkit.command.CommandSender

@Service("area_context", singleton = false)
interface IAreaContext : ISetter {
    fun manager(): IAreaManager
    fun addGame(game: Game)
    fun validate(sender: CommandSender? = null)

    companion object {
        fun get(manager: IAreaManager): IAreaContext =
            Mediator.get(IAreaContext::class.java, ArgumentSet(arrayOf(manager)))!!
    }
}