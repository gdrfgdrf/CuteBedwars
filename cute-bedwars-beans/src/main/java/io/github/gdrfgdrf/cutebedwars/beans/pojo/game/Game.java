package io.github.gdrfgdrf.cutebedwars.beans.pojo.game;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.gdrfgdrf.cutebedwars.beans.annotation.PositiveNumber;
import io.github.gdrfgdrf.cutebedwars.beans.annotation.Undefinable;
import io.github.gdrfgdrf.cutebedwars.beans.base.PropertyConvertible;
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
public class Game implements PropertyConvertible {
    @Undefinable
    private Long id;
    @Undefinable
    @JsonProperty(value = "area-id")
    private Long areaId;

    @JsonProperty(value = "enable")
    private Boolean enable;

    private String name;

    @PositiveNumber
    @JsonProperty(value = "min-player")
    private int minPlayer;
    @PositiveNumber
    @JsonProperty(value = "max-player")
    private int maxPlayer;

    private Region region;

    private WaitingRoom waitingRoom;

    @JsonProperty(value = "spectator-spawnpoint-coordinate")
    private Coordinate spectatorSpawnpointCoordinate;

    @Undefinable
    @JsonProperty(value = "secondary-generator")
    private AutomaticGenerator secondaryGenerator;
    @Undefinable
    @JsonProperty(value = "tertiary-generator")
    private AutomaticGenerator tertiaryGenerator;

    @Undefinable
    private List<Team> teams = new ArrayList<>();

    @Override
    public Object convert(Class<?> targetType, Object obj) {
        if (targetType == String.class) {
            return obj.toString();
        }
        if (targetType == boolean.class || targetType == Boolean.class) {
            return Boolean.parseBoolean(obj.toString());
        }
        if (targetType == int.class || targetType == Integer.class) {
            return Integer.parseInt(obj.toString());
        }
        if (targetType == Region.class) {
            Region region = new Region();
            return region.convert(targetType, obj);
        }
        if (targetType == WaitingRoom.class) {
            WaitingRoom waitingRoom = new WaitingRoom();
            return waitingRoom.convert(targetType, obj);
        }
        return null;
    }
}
