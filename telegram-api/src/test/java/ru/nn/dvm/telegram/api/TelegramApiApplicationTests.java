package ru.nn.dvm.telegram.api;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import ru.nn.dvm.telegram.api.config.TestcontainersConfiguration;
import ru.nn.dvm.telegram.api.config.TgBotMockConfig;

@ActiveProfiles("test")
@Import(value = {TgBotMockConfig.class})
class TelegramApiApplicationTests extends TestcontainersConfiguration {

    @Test
    void contextLoads() {
    }

}
