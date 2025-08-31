package ru.nn.dvm.economca.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Config for bot property.
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties("bot")
public class BotProperty {

    private String name;
    private String token;
}
