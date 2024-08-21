package io.github.gdrfgdrf.cutebedwars.beans.pojo.villager;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate;
import lombok.Data;

/**
 * @author gdrfgdrf
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Villager {
    private VillagerType type;
    private Coordinate coordinate;
}
