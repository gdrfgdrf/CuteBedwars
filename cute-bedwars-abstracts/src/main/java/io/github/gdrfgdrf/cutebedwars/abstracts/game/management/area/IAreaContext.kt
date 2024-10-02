package io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area

import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.ISetter
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game.IGameContext
import io.github.gdrfgdrf.cutebedwars.beans.pojo.game.Game
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import org.bukkit.command.CommandSender

@Service("area_context", singleton = false)
interface IAreaContext : ISetter {
    fun initialize()

    fun manager(): IAreaManager

    fun createGame(name: String): IGameContext
    fun addGame(game: Game, addToBean: Boolean = true)
    fun addGame(gameContext: IGameContext, addToBean: Boolean = true)

    fun getGame(id: Long): IGameContext?
    fun getGame(name: String): List<IGameContext>

    fun games(): List<IGameContext>

    fun validate(sender: CommandSender? = null)

    companion object {
        fun new(manager: IAreaManager): IAreaContext =
            Mediator.get(IAreaContext::class.java, ArgumentSet(arrayOf(manager)))!!
    }
}