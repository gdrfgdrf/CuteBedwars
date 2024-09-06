package io.github.gdrfgdrf.cutebedwars.beans.pojo.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.gdrfgdrf.cutebedwars.beans.base.PropertyConvertible;
import lombok.Data;

/**
 * @author gdrfgdrf
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item implements PropertyConvertible {
    private String nbt;

    @SuppressWarnings("unchecked")
    @Override
    public <T> T convert(Class<?> targetType, Object obj) {
        return (T) obj.toString();
    }
}
