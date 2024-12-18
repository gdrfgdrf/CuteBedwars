package io.github.gdrfgdrf.cutebedwars.beans.pojo.area;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.AreaStatus;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.game.Game;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.GameStatus;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gdrfgdrf
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Area {
    private Long id;
    private String name;

    @JsonProperty(value = "default-template-id")
    private Long defaultTemplateId;
    private AreaStatus status = AreaStatus.DISABLED;

    @JsonProperty(value = "world-name")
    private String worldName;

    @JsonProperty(value = "lobby-world-name")
    private String lobbyWorldName;

    @JsonProperty(value = "lobby-spawnpoint-coordinate")
    private Coordinate lobbySpawnpointCoordinate;

    private List<Game> games = new ArrayList<>();
}
