package io.github.gdrfgdrf.cutebedwars.beans.pojo.game;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Region;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.generator.Generator;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.villager.Villager;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gdrfgdrf
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Team {
    private Long id;
    @JsonProperty(value = "game-id")
    private Long gameId;
    private String name;

    @JsonProperty(value = "min-player")
    private int minPlayer;
    @JsonProperty(value = "max-player")
    private int maxPlayer;

    @JsonProperty(value = "region")
    private Region region;

    @JsonProperty(value = "spawnpoint-coordinate")
    private Coordinate spawnpointCoordinate;
    @JsonProperty(value = "bed-coordinate")
    private Coordinate bedCoordinate;

    private List<Villager> villagers = new ArrayList<>();

    private Generator generator;
}
