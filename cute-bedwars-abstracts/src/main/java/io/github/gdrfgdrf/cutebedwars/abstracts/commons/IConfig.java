package io.github.gdrfgdrf.cutebedwars.abstracts.commons;

import java.lang.reflect.Method;

/**
 * @author gdrfgdrf
 */
public interface IConfig {
    void fulfill();

    @SuppressWarnings("unchecked")
    default <T> T get(String key) {
        try {
            Method method = this.getClass().getMethod("get" + key);
            return (T) method.invoke(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
