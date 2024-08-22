package io.github.gdrfgdrf.cutebedwars.beans.pojo.game;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.gdrfgdrf.cutebedwars.beans.annotation.Undefinable;
import io.github.gdrfgdrf.cutebedwars.beans.base.PropertyConvertible;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Region;
import lombok.Data;

/**
 * @author gdrfgdrf
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WaitingRoom implements PropertyConvertible {
    @Undefinable
    @JsonProperty(value = "game-id")
    private Long gameId;

    private Region region;
    @JsonProperty(value = "spawnpoint-coordinate")
    private Coordinate spawnpointCoordinate;

    @Override
    public Object convert(Class<?> targetType, Object obj) {
        if (targetType == Region.class) {
            Region region = new Region();
            return region.convert(targetType, obj);
        }
        if (targetType == Coordinate.class) {
            Coordinate coordinate = new Coordinate();
            return coordinate.convert(targetType, obj);
        }
        return null;
    }
}
