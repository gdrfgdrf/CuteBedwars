package io.github.gdrfgdrf.cutebedwars.database.impl.sqlite.service.impl

import io.github.gdrfgdrf.cutebedwars.beans.game.AbstractDatabaseTeam
import io.github.gdrfgdrf.cutebedwars.database.impl.sqlite.beans.game.Team
import io.github.gdrfgdrf.cutebedwars.database.impl.sqlite.mapper.TeamMapper
import io.github.gdrfgdrf.cutebedwars.database.impl.sqlite.service.IITeamService
import io.github.gdrfgdrf.cutebedwars.database.impl.sqlite.service.base.BetterServiceImpl

class TeamServiceImpl : BetterServiceImpl<TeamMapper, Team>(), IITeamService {
    override fun insert(team: AbstractDatabaseTeam): Int {
        return super.insert(team as Team)
    }
}