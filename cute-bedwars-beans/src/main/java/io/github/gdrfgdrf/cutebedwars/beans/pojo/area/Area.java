package io.github.gdrfgdrf.cutebedwars.beans.pojo.area;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.gdrfgdrf.cutebedwars.beans.annotation.ConvertPropertyFunction;
import io.github.gdrfgdrf.cutebedwars.beans.annotation.UndefinableForPropertyChange;
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
public class Area {
    @UndefinableForPropertyChange
    private Long id;
    private String name;

    @JsonProperty(value = "default-template-id")
    private Long defaultTemplateId;
    private Status status = Status.DISABLED;

    @JsonProperty(value = "world-name")
    private String worldName;

    @JsonProperty(value = "lobby-world-name")
    private String lobbyWorldName;

    @JsonProperty(value = "lobby-spawnpoint-coordinate")
    @UndefinableForPropertyChange
    private Coordinate lobbySpawnpointCoordinate;

    @UndefinableForPropertyChange
    private List<Game> games = new ArrayList<>();

    @SuppressWarnings("unchecked")
    @ConvertPropertyFunction
    public static <T> T convert(Class<?> targetType, Object obj) {
        if (targetType == String.class) {
            return (T) obj.toString();
        }
        if (targetType == long.class || targetType == Long.class) {
            return (T) Long.valueOf(Long.parseLong(obj.toString()));
        }
        return null;
    }
}
