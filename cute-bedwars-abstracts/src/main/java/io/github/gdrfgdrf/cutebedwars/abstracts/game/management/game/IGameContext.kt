package io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game

import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaContext
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.team.ITeamContext
import io.github.gdrfgdrf.cutebedwars.abstracts.storage.AbstractGameCommitStorage
import io.github.gdrfgdrf.cutebedwars.beans.pojo.game.Game
import io.github.gdrfgdrf.cutebedwars.beans.pojo.team.Team
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import org.bukkit.command.CommandSender

@Service("game_context", singleton = false)
interface IGameContext {
    val areaContext: IAreaContext
    val game: Game
    val commitStorage: AbstractGameCommitStorage
    val teams: List<ITeamContext>

    fun addTeam(team: Team)

    companion object {
        fun new(game: Game): IGameContext = Mediator.get(IGameContext::class.java, ArgumentSet(arrayOf(game)))!!
    }
}