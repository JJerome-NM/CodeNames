package com.codenames.properties;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "default-messages-paths")
@Getter @Setter
public class DefaultMessagePathProperties {

    private String newRoomInfoPath;

}
