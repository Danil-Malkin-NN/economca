package ru.nn.dvm.telegram.api.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.nn.dvm.telegram.api.config.BotProperty;

import java.util.ArrayList;

/**
 * Telegram bot implementation.
 */
@Slf4j
@Component
public class TgBot extends TelegramLongPollingBot {

    private final BotProperty property;

    public TgBot(BotProperty property) {
        super(property.getToken());
        this.property = property;
    }

    @Override
    public void onUpdateReceived(Update update) {
        log.info("Start handling update with id: {}", update.getUpdateId());
        log.info("Update: {}", update);
        log.info("End handling update with id: {}", update.getUpdateId());

        SendMessage message = new SendMessage();
        message.setText("Hello world");
        //TODO NPE!!!
        message.setChatId(update.getMessage().getChatId());
        message.enableHtml(true);

        InlineKeyboardMarkup replyMarkup = new InlineKeyboardMarkup();
        //TODO тут можно добавлять кнопки к сообщениям.
        replyMarkup.setKeyboard(new ArrayList<>());
        message.setReplyMarkup(replyMarkup);

        sendMessage(message);
    }

    @Override
    public String getBotUsername() {
        return property.getName();
    }

    public void sendMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Failed send message to telegram. ChatId: {}", message.getChatId(), e);
        }
    }
}
