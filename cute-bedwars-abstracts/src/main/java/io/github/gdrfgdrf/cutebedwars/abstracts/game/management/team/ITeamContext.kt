package io.github.gdrfgdrf.cutebedwars.abstracts.game.management.team

import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.ISetter
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game.IGameContext
import io.github.gdrfgdrf.cutebedwars.beans.pojo.team.Team
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import org.bukkit.command.CommandSender

@Service("team_context", singleton = false)
interface ITeamContext : ISetter {
    fun validate(sender: CommandSender? = null, withHeader: Boolean = false): Boolean

    companion object {
        fun new(gameContext: IGameContext, team: Team): ITeamContext = Mediator.get(
            ITeamContext::class.java, ArgumentSet(
                arrayOf(gameContext, team)
            )
        )!!
    }
}