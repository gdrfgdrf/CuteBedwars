package io.github.gdrfgdrf.cutebedwars.database.impl.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IConfig;
import lombok.Data;

/**
 * @author gdrfgdrf
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DefaultDatabaseConfig implements IConfig {
    public static DefaultDatabaseConfig instance = null;

    @JsonProperty(value = "enable-database-logging")
    private Boolean enableDatabaseLogging;

    @JsonProperty(value = "database-username")
    private String databaseUsername;

    @JsonProperty(value = "database-password")
    private String databasePassword;

    public static void reset(DefaultDatabaseConfig config) {
        config.enableDatabaseLogging = null;
        config.databaseUsername = null;
        config.databasePassword = null;
        config.fulfill();
    }

    @Override
    public void fulfill() {
        enableDatabaseLogging = false;
        databaseUsername = "";
        databasePassword = "";
    }

    public static <T> T value(String key) {
        return instance.get(key);
    }
}
