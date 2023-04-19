package com.codenames.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "codenames-words")
@Getter @Setter
public class WordsProperties {

    private String resourcesPath;

    private String fileName;

}
