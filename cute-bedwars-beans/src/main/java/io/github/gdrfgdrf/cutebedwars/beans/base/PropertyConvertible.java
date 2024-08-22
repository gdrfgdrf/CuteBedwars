package io.github.gdrfgdrf.cutebedwars.beans.base;

/**
 * @author gdrfgdrf
 */
public interface PropertyConvertible {
    Object convert(Class<?> targetType, Object obj);
}
