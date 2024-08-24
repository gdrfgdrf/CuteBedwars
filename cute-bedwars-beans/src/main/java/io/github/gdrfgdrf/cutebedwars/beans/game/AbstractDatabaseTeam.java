package io.github.gdrfgdrf.cutebedwars.beans.game;

import java.util.List;

/**
 * @author gdrfgdrf
 */
public abstract class AbstractDatabaseTeam {
    public abstract Long getId();
    public abstract void setId(Long id);

    public abstract Long getGameId();
    public abstract void setGameId(Long gameId);

    public abstract List<AbstractDatabasePlayer> getGamePlayers();
}
