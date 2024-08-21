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
    @JsonProperty(value = "top-front-left")
    private Coordinate topFrontLeft;
    @JsonProperty(value = "top-front-right")
    private Coordinate topFrontRight;
    @JsonProperty(value = "top-back-left")
    private Coordinate topBackLeft;
    @JsonProperty(value = "top-back-right")
    private Coordinate topBackRight;

    @JsonProperty(value = "bottom-front-left")
    private Coordinate bottomFrontLeft;
    @JsonProperty(value = "bottom-front-right")
    private Coordinate bottomFrontRight;
    @JsonProperty(value = "bottom-back-left")
    private Coordinate bottomBackLeft;
    @JsonProperty(value = "bottom-back-right")
    private Coordinate bottomBackRight;
}
