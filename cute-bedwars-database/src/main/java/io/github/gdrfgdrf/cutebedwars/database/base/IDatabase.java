package io.github.gdrfgdrf.cutebedwars.database.base;

/**
 * @author gdrfgdrf
 */
public interface IDatabase {
    String getDisplayName();
    void load() throws Exception;
    void close() throws Exception;

    <T> T getService(Class<? extends IService> serviceClass);
}
