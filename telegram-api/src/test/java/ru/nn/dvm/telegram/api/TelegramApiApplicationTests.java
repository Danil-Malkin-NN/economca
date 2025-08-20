package ru.nn.dvm.telegram.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import ru.nn.dvm.telegram.api.config.TgBotMockConfig;

@ActiveProfiles("test")
@Import(value = {TgBotMockConfig.class})
@SpringBootTest
class TelegramApiApplicationTests {

    @Test
    void contextLoads() {
    }

}
