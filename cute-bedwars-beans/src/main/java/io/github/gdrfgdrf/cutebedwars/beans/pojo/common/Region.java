package io.github.gdrfgdrf.cutebedwars.beans.pojo.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.gdrfgdrf.cutebedwars.beans.base.PropertyConvertible;
import lombok.Data;

/**
 * @author gdrfgdrf
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Region implements PropertyConvertible {
    @JsonProperty(value = "first-coordinate")
    private Coordinate firstCoordinate;
    @JsonProperty(value = "second-coordinate")
    private Coordinate secondCoordinate;

    @SuppressWarnings("unchecked")
    @Override
    public <T> T convert(Class<?> targetType, Object obj) {
        String[] split = obj.toString().split(" ");
        Coordinate coordinate = new Coordinate();
        for (String s : split) {
            coordinate.convert(float.class, s);
        }
        return (T) coordinate;
    }
}
