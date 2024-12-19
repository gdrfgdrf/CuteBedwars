package io.github.gdrfgdrf.cutebedwars.beans.pojo.game;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Region;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.GameStatus;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.generator.GeneratorGroup;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.team.Team;
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

    private String name;
    private GameStatus status = GameStatus.DISABLED;

    @JsonProperty(value = "min-player")
    private int minPlayer;
    @JsonProperty(value = "max-player")
    private int maxPlayer;

    private Region region;

    @JsonProperty(value = "waiting-room")
    private WaitingRoom waitingRoom;

    @JsonProperty(value = "spectator-spawnpoint-coordinate")
    private Coordinate spectatorSpawnpointCoordinate;

    @JsonProperty(value = "generator-groups")
    private List<GeneratorGroup> generatorGroups = new ArrayList<>();

    private List<Team> teams = new ArrayList<>();
}
