package io.github.gdrfgdrf.cutebedwars.beans.base;

/**
 * @author gdrfgdrf
 */
public interface PropertyConvertible {
    <T> T convert(Class<?> targetType, Object obj);
}
