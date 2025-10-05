package ru.nn.dvm.telegram.api.service.steps.registry;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.nn.dvm.core.service.EconomService;
import ru.nn.dvm.telegram.api.service.steps.StepService;

import java.util.ArrayList;
import java.util.List;

@Service(value = "/start")
@RequiredArgsConstructor
public class StartStep implements StepService {

    private final EconomService service;

    private final String textMessage = """
            Рад тебя приветствовать, %USER_NAME%, я бот экономка.
            Я могу помочь тебе поставить бюджет на месяц и корректировать свои траты день ото дня, в зависимости от размера.
            Готовы задать цель?
            """;

    @Override
    public SendMessage process(Update update) {

        Message message = update.getMessage();
        User from = message.getFrom();
        service.registerUser(from.getId(), from.getUserName());

        SendMessage answer = new SendMessage();
        answer.setText(textMessage.replace("%USER_NAME%", from.getUserName()));
        //TODO NPE!!!
        answer.setChatId(update.getMessage()
                                 .getChatId());
        answer.enableHtml(true);

        InlineKeyboardMarkup replyMarkup = new InlineKeyboardMarkup();
        //TODO тут можно добавлять кнопки к сообщениям.
        replyMarkup.setKeyboard(getNextStepsButtons());
        answer.setReplyMarkup(replyMarkup);
        return answer;
    }

    private @NonNull List<List<InlineKeyboardButton>> getNextStepsButtons() {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> button1 = new ArrayList<>();
        buttons.add(button1);
        InlineKeyboardButton button = new InlineKeyboardButton("Добавить цель");
        button.setCallbackData("/add_target");
        button1.add(button);
        return buttons;
    }

}
