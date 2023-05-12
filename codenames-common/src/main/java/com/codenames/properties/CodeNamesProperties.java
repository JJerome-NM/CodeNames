package com.codenames.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "codenames")
@Getter @Setter
public class CodeNamesProperties {
    WordsProperties words;
    GameProperties gameRooms;
    WSResponsePathProperties wsResponsePaths;
    RestControllerProperties restController;
}
