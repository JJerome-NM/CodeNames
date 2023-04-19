package com.codenames.properties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "codenames-game")
@Getter @Setter
public class CodeNamesGameProperties {

    private int minRoomId;

    private int maxRoomId;
}
