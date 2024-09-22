package io.github.gdrfgdrf.cutebedwars.beans.pojo.generator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.gdrfgdrf.cutebedwars.beans.annotation.Undefinable;
import io.github.gdrfgdrf.cutebedwars.beans.base.PropertyConvertible;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.generator.level.GeneratorGroupLevel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gdrfgdrf
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeneratorGroup implements PropertyConvertible {
    @JsonProperty(value = "display-order")
    private int displayOrder;
    @JsonProperty(value = "display-name")
    private String displayName;
    @Undefinable
    private List<Generator> generators = new ArrayList<>();
    @Undefinable
    private List<GeneratorGroupLevel> levels = new ArrayList<>();

    @SuppressWarnings("unchecked")
    @Override
    public <T> T convert(Class<?> targetType, Object obj) {
        if (targetType == int.class || targetType == Integer.class) {
            return (T) Integer.valueOf(Integer.parseInt(obj.toString()));
        }
        return null;
    }
}
