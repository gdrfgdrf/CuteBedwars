package io.github.gdrfgdrf.cutebedwars.beans.game;

import java.util.List;

/**
 * @author gdrfgdrf
 */
public abstract class AbstractGame {
    public abstract Long getId();
    public abstract void setId(Long id);

    public abstract List<AbstractTeam> getTeams();
}
