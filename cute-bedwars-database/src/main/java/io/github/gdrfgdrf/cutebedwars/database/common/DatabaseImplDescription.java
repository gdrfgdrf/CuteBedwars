package io.github.gdrfgdrf.cutebedwars.database.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author gdrfgdrf
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DatabaseImplDescription {
    @JsonProperty(value = "database-impl-class")
    private String databaseImplClass;
}
