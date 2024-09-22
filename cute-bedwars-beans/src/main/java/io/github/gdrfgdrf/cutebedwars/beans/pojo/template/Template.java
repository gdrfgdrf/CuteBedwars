package io.github.gdrfgdrf.cutebedwars.beans.pojo.template;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.gdrfgdrf.cutebedwars.beans.annotation.ConvertPropertyFunction;
import io.github.gdrfgdrf.cutebedwars.beans.annotation.UndefinableForPropertyChange;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.game.Game;
import lombok.Data;

/**
 * @author gdrfgdrf
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Template {
    @UndefinableForPropertyChange
    private Long id;
    private String name;
    @UndefinableForPropertyChange
    private Game game;

    @SuppressWarnings("unchecked")
    @ConvertPropertyFunction
    public static <T> T convert(Class<?> targetType, Object obj) {
        if (targetType == String.class) {
            return (T) obj.toString();
        }
        return null;
    }
}
