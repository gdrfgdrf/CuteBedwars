package io.github.gdrfgdrf.cutebedwars.beans.game;

import java.util.List;

/**
 * @author gdrfgdrf
 */
public abstract class AbstractDatabaseGame {
    public abstract Long getId();
    public abstract void setId(Long id);

    public abstract List<AbstractDatabaseTeam> getTeams();
}
