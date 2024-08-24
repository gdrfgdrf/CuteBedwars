package io.github.gdrfgdrf.cutebedwars.beans.pojo.team;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.gdrfgdrf.cutebedwars.beans.annotation.PositiveNumber;
import io.github.gdrfgdrf.cutebedwars.beans.annotation.Undefinable;
import io.github.gdrfgdrf.cutebedwars.beans.base.PropertyConvertible;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Region;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.game.Game;
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
public class Team implements PropertyConvertible {
    @Undefinable
    private Long id;
    @Undefinable
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

    @JsonProperty(value = "region")
    private Region region;
    @JsonProperty(value = "operable-coordinates")
    private List<Coordinate> operableCoordinates = new ArrayList<>();

    @JsonProperty(value = "spawnpoint-coordinate")
    private Coordinate spawnpointCoordinate;
    @JsonProperty(value = "bed-coordinate")
    private Coordinate bedCoordinate;

    @Undefinable
    private List<Villager> villagers = new ArrayList<>();

    @Undefinable
    private Generator generator;

    @Override
    public Object convert(Class<?> targetType, Object obj) {
        if (targetType == String.class) {
            return obj.toString();
        }
        if (targetType == int.class || targetType == Integer.class) {
            return Integer.parseInt(obj.toString());
        }
        if (targetType == Region.class) {
            Region region = new Region();
            return region.convert(Coordinate.class, obj);
        }
        if (targetType == Coordinate.class) {
            Coordinate coordinate = new Coordinate();
            return coordinate.convert(float.class, obj);
        }
        return null;
    }

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
