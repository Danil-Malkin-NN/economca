package ru.nn.dvm.telegram.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableJpaRepositories(basePackages = "ru.nn.dvm.core")
@EntityScan(basePackages = "ru.nn.dvm.core.entity")
@SpringBootApplication(scanBasePackages = "ru.nn.dvm")
public class TelegramApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelegramApiApplication.class, args);
    }

}
