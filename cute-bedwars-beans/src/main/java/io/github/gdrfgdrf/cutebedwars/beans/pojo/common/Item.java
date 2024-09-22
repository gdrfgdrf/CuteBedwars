package io.github.gdrfgdrf.cutebedwars.beans.pojo.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.gdrfgdrf.cutebedwars.beans.annotation.ConvertPropertyFunction;
import io.github.gdrfgdrf.cutebedwars.beans.annotation.UndefinableForPropertyChange;
import lombok.Data;

/**
 * @author gdrfgdrf
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
    @UndefinableForPropertyChange
    private String nbt;

    @SuppressWarnings("unchecked")
    @ConvertPropertyFunction
    public static  <T> T convert(Class<?> targetType, Object obj) {
        return (T) obj.toString();
    }
}
