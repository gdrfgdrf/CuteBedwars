package io.github.gdrfgdrf.cutebedwars.database.impl.mysql.common;

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
public class DefaultMySQLDatabaseConfig implements IConfig {
    public static DefaultMySQLDatabaseConfig instance = null;

    @JsonProperty(value = "datasource-properties-filename")
    private String dataSourcePropertiesFileName;

    @JsonProperty(value = "enable-database-logging")
    private Boolean enableDatabaseLogging;

    public static void reset(DefaultMySQLDatabaseConfig config) {
        config.dataSourcePropertiesFileName = null;
        config.enableDatabaseLogging = null;
        config.fulfill();
    }

    @Override
    public void fulfill() {
        dataSourcePropertiesFileName = IConstants.Companion.get("ANOTHER_CONFIG_PATH") + "default-default-mysql-datasource.properties";
        enableDatabaseLogging = false;
    }

    public static <T> T value(String key) {
        return instance.get(key);
    }
}
