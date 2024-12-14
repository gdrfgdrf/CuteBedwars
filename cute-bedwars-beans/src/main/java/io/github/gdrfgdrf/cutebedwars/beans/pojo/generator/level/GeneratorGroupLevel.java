package io.github.gdrfgdrf.cutebedwars.beans.pojo.generator.level;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author gdrfgdrf
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeneratorGroupLevel {
    private int order;
    @JsonProperty(value = "generate-cost")
    private long generateCost;
    @JsonProperty(value = "display-name")
    private String displayName;
}
