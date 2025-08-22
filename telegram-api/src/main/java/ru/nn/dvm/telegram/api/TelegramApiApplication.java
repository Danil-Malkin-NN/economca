package ru.nn.dvm.telegram.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.nn.dvm")
public class TelegramApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelegramApiApplication.class, args);
    }

}
