package io.github.gdrfgdrf.cutebedwars.beans.pojo.team;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.gdrfgdrf.cutebedwars.beans.annotation.ConvertPropertyFunction;
import io.github.gdrfgdrf.cutebedwars.beans.annotation.PositiveNumber;
import io.github.gdrfgdrf.cutebedwars.beans.annotation.UndefinableForPropertyChange;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Region;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.game.Game;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.generator.GeneratorGroup;
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
    @UndefinableForPropertyChange
    private Long id;
    @UndefinableForPropertyChange
    @JsonProperty(value = "game-id")
    private Long gameId;
    private String name;
    private TeamColor color;

    @PositiveNumber
    @JsonProperty(value = "min-player")
    private int minPlayer;
    @PositiveNumber
    @JsonProperty(value = "max-player")
    private int maxPlayer;

    @UndefinableForPropertyChange
    @JsonProperty(value = "region")
    private Region region;
    @UndefinableForPropertyChange
    @JsonProperty(value = "operable-coordinates")
    private List<Coordinate> operableCoordinates = new ArrayList<>();

    @UndefinableForPropertyChange
    @JsonProperty(value = "spawnpoint-coordinate")
    private Coordinate spawnpointCoordinate;
    @UndefinableForPropertyChange
    @JsonProperty(value = "bed-coordinate")
    private Coordinate bedCoordinate;

    @UndefinableForPropertyChange
    private List<Villager> villagers = new ArrayList<>();

    @UndefinableForPropertyChange
    @JsonProperty(value = "generator-groups")
    private List<GeneratorGroup> generatorGroups = new ArrayList<>();

    public boolean fixGameMinPlayer(Game game) {
        int totalMinPlayer = 0;
        List<Team> teams = game.getTeams();
        for (Team team : teams) {
            totalMinPlayer = totalMinPlayer + team.minPlayer;
        }
        if (totalMinPlayer == 0) {
            return false;
        }
        if (game.getMinPlayer() >= totalMinPlayer) {
            return false;
        }
        game.setMinPlayer(totalMinPlayer);
        return true;
    }

    public boolean fixGameMaxPlayer(Game game) {
        int totalMaxPlayer = 0;
        List<Team> teams = game.getTeams();
        for (Team team : teams) {
            totalMaxPlayer = totalMaxPlayer + team.maxPlayer;
        }
        if (totalMaxPlayer == 0) {
            return false;
        }
        if (game.getMaxPlayer() <= totalMaxPlayer) {
            return false;
        }
        game.setMaxPlayer(totalMaxPlayer);
        return true;
    }
}
