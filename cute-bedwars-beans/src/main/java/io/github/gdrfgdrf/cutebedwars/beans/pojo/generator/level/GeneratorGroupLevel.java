package io.github.gdrfgdrf.cutebedwars.beans.pojo.generator.level;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.gdrfgdrf.cutebedwars.beans.annotation.ConvertPropertyFunction;
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

    @SuppressWarnings("unchecked")
    @ConvertPropertyFunction
    public static <T> T convert(Class<?> targetType, Object obj) {
        if (targetType == String.class) {
            return (T) obj.toString();
        }
        if (targetType == int.class || targetType == Integer.class) {
            return (T) Integer.valueOf(Integer.parseInt(obj.toString()));
        }
        return null;
    }
}
