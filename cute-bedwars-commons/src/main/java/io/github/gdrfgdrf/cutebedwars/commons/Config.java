package io.github.gdrfgdrf.cutebedwars.commons;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IConfig;
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;

/**
 * @author gdrfgdrf
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ServiceImpl(("config"))
public class Config implements IConfig {
    public static IConfig INSTANCE;

    @JsonProperty(defaultValue = "chinese_simplified")
    private String language;

    @JsonProperty(value = "worker-id")
    private short workerId;

    @JsonProperty(value = "default-sqlite")
    private String databaseImpl;

    @JsonProperty(value = "enable-database-logging")
    private Boolean enableDatabaseLogging;

    @JsonProperty(value = "database-username")
    private String databaseUsername;

    @JsonProperty(value = "database-password")
    private String databasePassword;

    @JsonProperty(value = "request-timeout")
    private Long requestTimeout;

//    @JsonProperty(value = "area-auto-save-delay")
//    private Long areaAutoSaveDelay;

    public static void reset(Config config) {
        config.language = "chinese_simplified";
        config.workerId = 0;
        config.databaseImpl = "default-sqlite";
        config.databaseUsername = "";
        config.databasePassword = "";
        config.enableDatabaseLogging = false;
        config.requestTimeout = 10000L;
    }

    @Override
    @SuppressWarnings("ALL")
    public <T> T get(@NotNull String key) {
        try {
            Method method = Config.class.getMethod("get" + key);
            return (T) method.invoke(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
