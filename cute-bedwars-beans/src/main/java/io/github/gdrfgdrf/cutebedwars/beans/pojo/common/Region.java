package io.github.gdrfgdrf.cutebedwars.beans.pojo.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author gdrfgdrf
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Region {
    @JsonProperty(value = "pos1")
    private Coordinate pos1;
    @JsonProperty(value = "pos2")
    private Coordinate pos2;
}
