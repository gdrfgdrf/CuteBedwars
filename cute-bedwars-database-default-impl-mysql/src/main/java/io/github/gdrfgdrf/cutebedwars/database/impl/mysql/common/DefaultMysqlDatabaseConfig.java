package io.github.gdrfgdrf.cutebedwars.database.impl.mysql.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IConfig;
import lombok.Data;

/**
 * @author gdrfgdrf
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DefaultMysqlDatabaseConfig implements IConfig {
    public static DefaultMysqlDatabaseConfig instance = null;

    @JsonProperty(value = "driver")
    private String driver;

    @JsonProperty(value = "url")
    private String url;

    @JsonProperty(value = "enable-database-logging")
    private Boolean enableDatabaseLogging;

    @JsonProperty(value = "database-username")
    private String databaseUsername;

    @JsonProperty(value = "database-password")
    private String databasePassword;

    public static void reset(DefaultMysqlDatabaseConfig config) {
        config.driver = null;
        config.url = null;
        config.enableDatabaseLogging = null;
        config.databaseUsername = null;
        config.databasePassword = null;
        config.fulfill();
    }

    @Override
    public void fulfill() {
        driver = "com.mysql.jdbc.Driver";
        url = "";
        enableDatabaseLogging = false;
        databaseUsername = "";
        databasePassword = "";
    }

    public static <T> T value(String key) {
        return instance.get(key);
    }
}
