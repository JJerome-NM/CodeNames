package com.codenames.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;
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

    @Value
    public static class WordsProperties {

        String resourcesPath;

        String filePrefix;
    }

    @Value
    public static class GameProperties {

        int minRoomId;

        int maxRoomId;
    }

    @Value
    public static class WSResponsePathProperties {

        String newRoomInfoPath;
    }

    @Value
    public static class RestControllerProperties {

        String[] crossOrigin;
    }

    @Value
    public static class RedisProperties {

        String userRoomSessionsHashKey;
    }

    @Value
    public static class SecurityProperties {

        String jwt;
    }
}
