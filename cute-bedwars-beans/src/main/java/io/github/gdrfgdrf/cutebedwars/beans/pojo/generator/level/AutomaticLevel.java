package io.github.gdrfgdrf.cutebedwars.beans.pojo.generator.level;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author gdrfgdrf
 */
@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AutomaticLevel extends GeneratorLevel {
    @JsonProperty(value = "next-level-cost")
    private long nextLevelCost;
}
