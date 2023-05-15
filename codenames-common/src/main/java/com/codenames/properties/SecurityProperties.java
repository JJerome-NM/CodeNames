package com.codenames.properties;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Setter @Getter
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {
    String jwt;
}
