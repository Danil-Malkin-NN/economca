package ru.nn.dvm.telegram.api.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Свойства бота из конфигурации Spring: отображаемое имя и токен API.
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties("bot")
public class BotProperty {

    private String name;
    private String token;
}
