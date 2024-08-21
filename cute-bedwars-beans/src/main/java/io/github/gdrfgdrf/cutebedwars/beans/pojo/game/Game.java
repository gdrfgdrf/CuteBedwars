package io.github.gdrfgdrf.cutebedwars.beans.pojo.game;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Region;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.generator.AutomaticGenerator;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gdrfgdrf
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Game {
    private Long id;
    @JsonProperty(value = "area-id")
    private Long areaId;

    @JsonProperty(value = "min-player")
    private int minPlayer;
    @JsonProperty(value = "max-player")
    private int maxPlayer;

    private Region region;

    private WaitingRoom waitingRoom;

    @JsonProperty(value = "spectator-spawnpoint-coordinate")
    private Coordinate spectatorSpawnpointCoordinate;

    @JsonProperty(value = "secondary-generator")
    private AutomaticGenerator secondaryGenerator;
    @JsonProperty(value = "tertiary-generator")
    private AutomaticGenerator tertiaryGenerator;

    private List<Team> teams = new ArrayList<>();
}
