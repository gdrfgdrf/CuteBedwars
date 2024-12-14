package io.github.gdrfgdrf.cutebedwars.beans.pojo.game;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Region;
import lombok.Data;

/**
 * @author gdrfgdrf
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WaitingRoom {
    private Region region;
    @JsonProperty(value = "spawnpoint-coordinate")
    private Coordinate spawnpointCoordinate;
}
