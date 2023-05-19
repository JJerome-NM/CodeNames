package com.codenames.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "codenames.rest-controller")
@Getter @Setter
public class RestControllerProperties {

    @Value("${codenames.rest-controller.cross-origin:}")
    String[] crossOrigin;
}
