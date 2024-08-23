package io.github.gdrfgdrf.cutebedwars.beans.pojo.generator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Item;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.generator.level.GeneratorLevel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gdrfgdrf
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Generator {
    @JsonProperty(value = "generate-coordinate")
    private Coordinate generateCoordinate;

    private Item display;
    private List<Item> products = new ArrayList<>();
    private List<GeneratorLevel> levels = new ArrayList<>();
    private List<Coordinate> coordinates = new ArrayList<>();
}
