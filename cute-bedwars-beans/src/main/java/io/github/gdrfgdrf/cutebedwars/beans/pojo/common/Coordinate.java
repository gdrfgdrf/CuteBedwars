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
    private double x;
    private double y;
    private double z;

    @Override
    public Object convert(Class<?> targetType, Object obj) {
        return Double.parseDouble(obj.toString());
    }
}
