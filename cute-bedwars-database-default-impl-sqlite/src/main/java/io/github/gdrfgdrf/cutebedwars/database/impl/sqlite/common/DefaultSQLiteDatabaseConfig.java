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
public class DefaultSQLiteDatabaseConfig implements IConfig {
    public static DefaultSQLiteDatabaseConfig instance = null;

    @JsonProperty(value = "filename")
    private String fileName;

    @JsonProperty(value = "datasource-properties-filename")
    private String dataSourcePropertiesFileName;

    @JsonProperty(value = "enable-database-logging")
    private Boolean enableDatabaseLogging;

    public static void reset(DefaultSQLiteDatabaseConfig config) {
        config.fileName = null;
        config.dataSourcePropertiesFileName = null;
        config.enableDatabaseLogging = null;
        config.fulfill();
    }

    @Override
    public void fulfill() {
        fileName = IConstants.Companion.get("BASE_FOLDER") + "default-sqlite-database.db";
        dataSourcePropertiesFileName = IConstants.Companion.get("ANOTHER_CONFIG_PATH") + "default-default-sqlite-datasource.properties";
        enableDatabaseLogging = false;
    }

    public static <T> T value(String key) {
        return instance.get(key);
    }
}
