package io.github.gdrfgdrf.cutebedwars.game.management.game

import com.github.yitter.idgen.YitIdHelper
import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IConstants
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaContext
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game.IGameContext
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.team.ITeamContext
import io.github.gdrfgdrf.cutebedwars.abstracts.storage.AbstractGameCommitStorage
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.cutebedwars.beans.pojo.game.Game
import io.github.gdrfgdrf.cutebedwars.beans.pojo.team.Team
import io.github.gdrfgdrf.cutebedwars.game.management.team.TeamContext
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@ServiceImpl("game_context", needArgument = true)
class GameContext(
    argumentSet: ArgumentSet,
) : IGameContext {
    override val areaContext: IAreaContext = argumentSet.args[0] as IAreaContext
    override val game = argumentSet.args[1] as Game
    override val teams = ArrayList<ITeamContext>()

    override val commitStorage: AbstractGameCommitStorage

    init {
        if (game.id == null) {
            val id = YitIdHelper.nextId()
            "Set an id: $id to a game (area's id: ${game.areaId})".logInfo()
            game.id = id
        }
        if (game.name.isNullOrEmpty()) {
            "The game (area's id: ${game.areaId}, id: ${game.id})'s name is null, setting to \"temp_name_${game.id}\""
            game.name = "temp_name_${game.id}"
        }

        "Creating the commit storage for a game id: ${game.id}, name: ${game.name}, area's id: ${game.areaId}".logInfo()
        commitStorage = AbstractGameCommitStorage.new("${IConstants["AREA_FOLDER"]}/${game.areaId}/commits/games/${game.id}")
    }

    constructor(areaContext: IAreaContext, game: Game)
            : this(ArgumentSet(arrayOf(areaContext, game)))

    override fun addTeam(team: Team) {
        "Adding a team to a game id: ${game.id}, name: ${game.name}, area's id: ${game.areaId}".logInfo()
        teams.add(TeamContext(this, team))
    }
}