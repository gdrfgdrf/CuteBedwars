package io.github.gdrfgdrf.cutebedwars.database.service;

import io.github.gdrfgdrf.cutebedwars.beans.AbstractPlayerData;
import io.github.gdrfgdrf.cutebedwars.database.Database;
import io.github.gdrfgdrf.cutebedwars.database.base.IService;

import java.util.UUID;

/**
 * @author gdrfgdrf
 */
public interface IPlayerService extends IService {
    int insert(AbstractPlayerData playerData);
    boolean exist(UUID uuid);
    AbstractPlayerData selectByUuid(UUID uuid);

    static IPlayerService get() {
        return Database.get().getService(IPlayerService.class);
    }
}
