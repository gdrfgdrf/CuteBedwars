package io.github.gdrfgdrf.cutebedwars.commons;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author gdrfgdrf
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Config {
    public static Config INSTANCE;

    @JsonProperty(defaultValue = "chinese_simplified")
    private String language;

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

    public static void reset(Config config) {
        config.language = "chinese_simplified";
        config.databaseImpl = "default-sqlite";
        config.databaseUsername = "";
        config.databasePassword = "";
        config.enableDatabaseLogging = false;
        config.requestTimeout = 30000L;
    }
}
