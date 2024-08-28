package io.github.gdrfgdrf.cutebedwars.beans.pojo.area;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.gdrfgdrf.cutebedwars.beans.annotation.Undefinable;
import io.github.gdrfgdrf.cutebedwars.beans.base.PropertyConvertible;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.game.Game;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Status;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gdrfgdrf
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Area implements PropertyConvertible {
    @Undefinable
    private Long id;
    private String name;

    @Undefinable
    @JsonProperty(value = "default-template-id")
    private Long defaultTemplateId;
    private Status status = Status.DISABLED;

    @JsonProperty(value = "world-name")
    private String worldName;

    @JsonProperty(value = "lobby-world-name")
    private String lobbyWorldName;

    @JsonProperty(value = "lobby-spawnpoint-coordinate")
    private Coordinate lobbySpawnpointCoordinate;

    @Undefinable
    private List<Game> games = new ArrayList<>();

    @Override
    public Object convert(Class<?> targetType, Object obj) {
        if (targetType == String.class) {
            return obj.toString();
        }
        if (targetType == Status.class) {
            return Status.valueOf(obj.toString());
        }
        if (targetType == Coordinate.class) {
            Coordinate coordinate = new Coordinate();
            return coordinate.convert(targetType, obj);
        }
        return null;
    }
}
