package io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game

import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.ISetter
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaContext
import io.github.gdrfgdrf.cutebedwars.abstracts.storage.AbstractGameCommitStorage
import io.github.gdrfgdrf.cutebedwars.beans.pojo.game.Game
import io.github.gdrfgdrf.cutebedwars.beans.pojo.team.Team
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import org.bukkit.command.CommandSender

@Service("game_context", singleton = false)
interface IGameContext : ISetter {
    fun areaContext(): IAreaContext
    fun game(): Game
    fun commitStorage(): AbstractGameCommitStorage
    fun addTeam(team: Team)
    fun validate(sender: CommandSender? = null, withHeader: Boolean = false): Boolean

    companion object {
        fun new(game: Game): IGameContext = Mediator.get(IGameContext::class.java, ArgumentSet(arrayOf(game)))!!
    }
}