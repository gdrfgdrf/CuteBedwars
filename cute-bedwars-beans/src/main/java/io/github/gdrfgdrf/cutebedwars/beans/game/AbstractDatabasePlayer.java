package io.github.gdrfgdrf.cutebedwars.beans.game;

/**
 * @author gdrfgdrf
 */
public abstract class AbstractDatabasePlayer {
    public abstract Long getId();
    public abstract void setId(Long id);

    public abstract Long getGameId();
    public abstract void setGameId(Long gameId);

    public abstract Long getTeamId();
    public abstract void setTeamId(Long teamId);
}
