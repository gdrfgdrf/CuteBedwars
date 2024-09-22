package io.github.gdrfgdrf.cutebedwars.beans.pojo.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.gdrfgdrf.cutebedwars.beans.annotation.ConvertPropertyFunction;
import io.github.gdrfgdrf.cutebedwars.beans.annotation.UndefinableForPropertyChange;
import lombok.Data;

/**
 * @author gdrfgdrf
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Region {
    @UndefinableForPropertyChange
    @JsonProperty(value = "first-coordinate")
    private Coordinate firstCoordinate;
    @UndefinableForPropertyChange
    @JsonProperty(value = "second-coordinate")
    private Coordinate secondCoordinate;
}
