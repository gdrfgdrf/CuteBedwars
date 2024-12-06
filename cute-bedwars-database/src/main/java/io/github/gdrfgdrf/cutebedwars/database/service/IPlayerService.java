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
    int insert(Long id, UUID uuid);
    boolean exist(UUID uuid);
    AbstractPlayerData selectByUuid(UUID uuid);

    static IPlayerService instance() {
        return Database.instance().getService(IPlayerService.class);
    }
}
