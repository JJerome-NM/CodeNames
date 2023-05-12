package com.codenames.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "codenames-game-rooms")
@Getter @Setter
public class GameProperties {
    private int minRoomId;

    private int maxRoomId;
}
