package io.github.gdrfgdrf.cutebedwars.database.service;

import io.github.gdrfgdrf.cutebedwars.beans.game.AbstractDatabaseTeam;
import io.github.gdrfgdrf.cutebedwars.database.Database;
import io.github.gdrfgdrf.cutebedwars.database.base.IService;

/**
 * @author gdrfgdrf
 */
public interface ITeamService extends IService {
    int insert(AbstractDatabaseTeam team);

    static ITeamService instance() {
        return Database.instance().getService(ITeamService.class);
    }
}
