package io.github.gdrfgdrf.cutebedwars.database.impl.service.impl

import io.github.gdrfgdrf.cutebedwars.beans.game.AbstractDatabaseTeam
import io.github.gdrfgdrf.cutebedwars.database.impl.beans.game.Team
import io.github.gdrfgdrf.cutebedwars.database.impl.mapper.TeamMapper
import io.github.gdrfgdrf.cutebedwars.database.impl.service.IITeamService
import io.github.gdrfgdrf.cutebedwars.database.impl.service.base.BetterServiceImpl

class TeamServiceImpl : BetterServiceImpl<TeamMapper, Team>(), IITeamService {
    override fun insert(team: AbstractDatabaseTeam): Int {
        return super.insert(team as Team)
    }
}