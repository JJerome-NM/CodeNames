package com.codenames.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "codenames")
@Getter @Setter
public class CodeNamesProperties {

    @NestedConfigurationProperty
    private WordsProperties words;

    @NestedConfigurationProperty
    private GameProperties gameRooms;

    @NestedConfigurationProperty
    private WSResponsePathProperties wsResponsePaths;

    @NestedConfigurationProperty
    private RestControllerProperties restController;

    @NestedConfigurationProperty
    private RedisProperties redis;

    @NestedConfigurationProperty
    private SecurityProperties security;
}
