package io.github.gdrfgdrf.cutebedwars.beans.pojo.game;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gdrfgdrf
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Area {
    private Long id;
    @JsonProperty(value = "default-template-id")
    private Long defaultTemplateId;

    private String name;
    @JsonProperty(value = "world-name")
    private String worldName;
    private List<Game> games = new ArrayList<>();
}
