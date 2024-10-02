package io.github.gdrfgdrf.cutebedwars.beans.pojo.game;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.gdrfgdrf.cutebedwars.beans.annotation.ConvertPropertyFunction;
import io.github.gdrfgdrf.cutebedwars.beans.annotation.PositiveNumber;
import io.github.gdrfgdrf.cutebedwars.beans.annotation.UndefinableForPropertyChange;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Region;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Status;
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
    @UndefinableForPropertyChange
    private Long id;
    @UndefinableForPropertyChange
    @JsonProperty(value = "area-id")
    private Long areaId;

    private String name;
    private Status status = Status.DISABLED;

    @PositiveNumber
    @JsonProperty(value = "min-player")
    private int minPlayer;
    @PositiveNumber
    @JsonProperty(value = "max-player")
    private int maxPlayer;

    @UndefinableForPropertyChange
    private Region region;

    @JsonProperty(value = "waiting-room")
    @UndefinableForPropertyChange
    private WaitingRoom waitingRoom;

    @JsonProperty(value = "spectator-spawnpoint-coordinate")
    @UndefinableForPropertyChange
    private Coordinate spectatorSpawnpointCoordinate;

    @UndefinableForPropertyChange
    @JsonProperty(value = "generator-groups")
    private List<GeneratorGroup> generatorGroups = new ArrayList<>();

    @UndefinableForPropertyChange
    private List<Team> teams = new ArrayList<>();

    @SuppressWarnings("unchecked")
    @ConvertPropertyFunction
    public static  <T> T convert(Class<?> targetType, Object obj) {
        if (targetType == String.class) {
            return (T) obj.toString();
        }
        if (targetType == boolean.class || targetType == Boolean.class) {
            return (T) Boolean.valueOf(Boolean.parseBoolean(obj.toString()));
        }
        if (targetType == int.class || targetType == Integer.class) {
            return (T) Integer.valueOf(Integer.parseInt(obj.toString()));
        }
        return null;
    }
}
