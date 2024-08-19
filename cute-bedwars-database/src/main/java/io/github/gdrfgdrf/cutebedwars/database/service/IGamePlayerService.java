package io.github.gdrfgdrf.cutebedwars.database.service;

import io.github.gdrfgdrf.cutebedwars.beans.game.AbstractGamePlayer;
import io.github.gdrfgdrf.cutebedwars.database.Database;
import io.github.gdrfgdrf.cutebedwars.database.base.IService;

/**
 * @author gdrfgdrf
 */
public interface IGamePlayerService extends IService {
    int insert(AbstractGamePlayer gamePlayer);

    static IGamePlayerService get() {
        return Database.get().getService(IGamePlayerService.class);
    }
}
