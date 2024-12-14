package io.github.gdrfgdrf.cutebedwars.beans.pojo.generator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.gdrfgdrf.cutebedwars.beans.annotation.UndefinableForPropertyChange;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.generator.level.GeneratorGroupLevel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gdrfgdrf
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeneratorGroup {
    @JsonProperty(value = "display-order")
    private int displayOrder;
    @JsonProperty(value = "display-name")
    private String displayName;
    @UndefinableForPropertyChange
    private List<Generator> generators = new ArrayList<>();
    @UndefinableForPropertyChange
    private List<GeneratorGroupLevel> levels = new ArrayList<>();
}
