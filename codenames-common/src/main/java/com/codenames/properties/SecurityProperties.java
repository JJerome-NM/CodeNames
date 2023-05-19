package com.codenames.properties;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "codenames.security")
@Getter @Setter
public class SecurityProperties {

    @Value("${codenames.security.jwt:eyJhbGciOiJIUzI1NiIsInR5}")
    String jwt;
}
