package io.github.gdrfgdrf.cutebedwars.database.service;

import io.github.gdrfgdrf.cutebedwars.beans.game.AbstractDatabasePlayer;
import io.github.gdrfgdrf.cutebedwars.database.Database;
import io.github.gdrfgdrf.cutebedwars.database.base.IService;

/**
 * @author gdrfgdrf
 */
public interface IGamePlayerService extends IService {
    int insert(AbstractDatabasePlayer gamePlayer);

    static IGamePlayerService instance() {
        return Database.instance().getService(IGamePlayerService.class);
    }
}
