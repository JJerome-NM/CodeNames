package com.codenames.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "codenames.game-rooms")
@Getter @Setter
public class GameProperties {

    @Value("${codenames.game-rooms.min-room-id:0}")
    private int minRoomId;

    @Value("${codenames.game-rooms.max-room-id:1000000}")
    private int maxRoomId;
}
