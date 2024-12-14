package io.github.gdrfgdrf.cutebedwars.game.management.team

import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game.IGameContext
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.team.ITeamContext
import io.github.gdrfgdrf.cutebedwars.abstracts.notifications.INotifications
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IConvertors
import io.github.gdrfgdrf.cutebedwars.beans.pojo.team.Team
import io.github.gdrfgdrf.cutebedwars.languages.collect.AreaManagementLanguage
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.localizationScope
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import org.bukkit.command.CommandSender

@ServiceImpl("team_context", needArgument = true)
class TeamContext(
    argumentSet: ArgumentSet,
) : ITeamContext {
    private val gameContext: IGameContext = argumentSet.args[0] as IGameContext
    private val team = argumentSet.args[1] as Team

    constructor(gameContext: IGameContext, team: Team)
            : this(ArgumentSet(arrayOf(gameContext, team)))
}