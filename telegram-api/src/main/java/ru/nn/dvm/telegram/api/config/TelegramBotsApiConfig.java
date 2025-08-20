package ru.nn.dvm.telegram.api.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.nn.dvm.telegram.api.bot.TgBot;

/**
 * Class bot registration.
 */
@Configuration
@Profile("!test")
@EnableConfigurationProperties
public class TelegramBotsApiConfig {

    @Bean
    public TelegramBotsApi telegramBotsApi(TgBot bot) throws TelegramApiException {
        var api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(bot);
        return api;
    }
}
