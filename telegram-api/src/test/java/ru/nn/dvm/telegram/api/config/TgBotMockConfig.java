package ru.nn.dvm.telegram.api.config;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ru.nn.dvm.telegram.api.bot.TgBot;

@TestConfiguration
public class TgBotMockConfig {

    @Bean
    public TgBot tgBot() {
        return Mockito.mock(TgBot.class);
    }
}
