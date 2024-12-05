package io.github.gdrfgdrf.cutebedwars.database.impl.sqlite.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IConfig;
import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IConstants;
import lombok.Data;

/**
 * @author gdrfgdrf
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DefaultSqliteDatabaseConfig implements IConfig {
    public static DefaultSqliteDatabaseConfig instance = null;

    @JsonProperty(value = "filename")
    private String fileName;

    @JsonProperty(value = "enable-database-logging")
    private Boolean enableDatabaseLogging;

    @JsonProperty(value = "database-username")
    private String databaseUsername;

    @JsonProperty(value = "database-password")
    private String databasePassword;

    public static void reset(DefaultSqliteDatabaseConfig config) {
        config.fileName = null;
        config.enableDatabaseLogging = null;
        config.databaseUsername = null;
        config.databasePassword = null;
        config.fulfill();
    }

    @Override
    public void fulfill() {
        fileName = IConstants.Companion.get("BASE_FOLDER") + "default-database-sqlite.db";
        enableDatabaseLogging = false;
        databaseUsername = "";
        databasePassword = "";
    }

    public static <T> T value(String key) {
        return instance.get(key);
    }
}
