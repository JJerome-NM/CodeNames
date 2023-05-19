package com.codenames.properties;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "codenames.ws-response-paths")
@Getter @Setter
public class WSResponsePathProperties {

    @Value("${codenames.ws-response-paths.new-room-info-path:/room/new/info}")
    private String newRoomInfoPath;

}
