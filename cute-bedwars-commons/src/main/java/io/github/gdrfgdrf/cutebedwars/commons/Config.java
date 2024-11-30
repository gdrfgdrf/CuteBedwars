package io.github.gdrfgdrf.cutebedwars.commons;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IIConfig;
import io.github.gdrfgdrf.cutebedwars.beans.pojo.config.ThreadPoolServiceImpl;
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;

/**
 * @author gdrfgdrf
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ServiceImpl("config")
public class Config implements IIConfig {
    public static IIConfig INSTANCE;

    @JsonProperty(value = "enable-debug-logging")
    private Boolean enableDebugLogging;

    @JsonProperty(defaultValue = "chinese_simplified")
    private String language;

    @JsonProperty(value = "worker-id")
    private Short workerId;

    @JsonProperty(value = "default-sqlite")
    private String databaseImpl;

    @JsonProperty(value = "request-timeout")
    private Long requestTimeout;

    @JsonProperty(value = "chat-page-cache-builder-specification")
    private String chatPageCacheBuilderSpecification;

    @JsonProperty(value = "thread-pool-service-impl")
    private ThreadPoolServiceImpl threadPoolServiceImpl;

    public static void reset(Config config) {
        config.enableDebugLogging = null;
        config.language = null;
        config.workerId = null;
        config.databaseImpl = null;
        config.requestTimeout = null;
        config.chatPageCacheBuilderSpecification = null;
        config.threadPoolServiceImpl = null;
        config.fulfill();
    }

    @Override
    public void fulfill() {
        if (enableDebugLogging == null) {
            enableDebugLogging = false;
        }
        if (language == null) {
            language = "chinese_simplified";
        }
        if (workerId == null) {
            workerId = 0;
        }
        if (databaseImpl == null) {
            databaseImpl = "default-sqlite";
        }
        if (requestTimeout == null) {
            requestTimeout = 10000L;
        }
        if (chatPageCacheBuilderSpecification == null) {
            chatPageCacheBuilderSpecification = "initialCapacity=100,maximumSize=1000,expireAfterAccess=1m";
        }
        if (threadPoolServiceImpl == null) {
            threadPoolServiceImpl = ThreadPoolServiceImpl.KOTLIN_COROUTINE;
        }
    }
}
