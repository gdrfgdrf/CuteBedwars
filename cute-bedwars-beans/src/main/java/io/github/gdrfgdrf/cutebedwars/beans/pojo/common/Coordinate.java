package io.github.gdrfgdrf.cutebedwars.beans.pojo.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.gdrfgdrf.cutebedwars.beans.base.PropertyConvertible;
import lombok.Data;

/**
 * @author gdrfgdrf
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Coordinate implements PropertyConvertible {
    private float x;
    private float y;
    private float z;

    @Override
    public Object convert(Class<?> targetType, Object obj) {
        return Float.parseFloat(obj.toString());
    }
}
