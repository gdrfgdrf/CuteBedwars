package io.github.gdrfgdrf.cutebedwars.beans;

import java.util.UUID;

/**
 * @author gdrfgdrf
 */
public abstract class AbstractPlayerData {
    public abstract Long getId();
    public abstract void setId(Long id);

    public abstract UUID getUuid();
    public abstract void setUuid(UUID uuid);
}
