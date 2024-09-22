package io.github.gdrfgdrf.cutebedwars.beans.pojo.generator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.gdrfgdrf.cutebedwars.beans.annotation.ConvertPropertyFunction;
import io.github.gdrfgdrf.cutebedwars.beans.annotation.UndefinableForPropertyChange;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Item;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Region;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gdrfgdrf
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Generator {
    @UndefinableForPropertyChange
    @JsonProperty(value = "region")
    private Region region;
    @UndefinableForPropertyChange
    @JsonProperty(value = "operable-coordinates")
    private List<Coordinate> operableCoordinates;

    @JsonProperty(value = "display-name")
    private String displayName;
    @UndefinableForPropertyChange
    @JsonProperty(value = "generate-coordinate")
    private Coordinate generateCoordinate;

    @UndefinableForPropertyChange
    private Item display;
    @UndefinableForPropertyChange
    private List<Item> products = new ArrayList<>();

    @SuppressWarnings("unchecked")
    @ConvertPropertyFunction
    public static <T> T convert(Class<?> targetType, Object obj) {
        if (targetType == String.class) {
            return (T) obj.toString();
        }
        return null;
    }
}
