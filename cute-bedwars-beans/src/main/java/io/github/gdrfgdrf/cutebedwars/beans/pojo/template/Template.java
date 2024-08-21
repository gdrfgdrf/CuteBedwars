package io.github.gdrfgdrf.cutebedwars.beans.pojo.template;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.game.Game;
import lombok.Data;

/**
 * @author gdrfgdrf
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Template {
    private Long id;
    private String name;
    private Game game;
}
