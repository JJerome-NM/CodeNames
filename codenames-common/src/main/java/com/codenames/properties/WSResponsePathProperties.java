package com.codenames.properties;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "codenames-response-paths")
@Getter @Setter
public class WSResponsePathProperties {

    private String newRoomInfoPath;

}
