package ru.nn.dvm.telegram.api.service.steps;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.nn.dvm.core.service.EconomService;
import ru.nn.dvm.telegram.api.service.NextStepService;
import ru.nn.dvm.telegram.api.service.storage.SaleBuffer;

import java.util.ArrayList;
import java.util.List;

@Service("/add_spending")
@AllArgsConstructor
public class SpendMoneyStep implements StepService {

    private final EconomService economService;
    private final SaleBuffer buffer;
    private final NextStepService nextStepService;

    private final String textMessage = "Выберете категорию траты";

    private List<Pair> categories;

    @PostConstruct
    public void init() {
        categories = new ArrayList<>();
        categories.add(new Pair("Продукты", "products"));
        categories.add(new Pair("Рестораны", "rest"));
        categories.add(new Pair("Дом", "home"));
        categories.add(new Pair("Транспорт", "cars"));
        categories.add(new Pair("Подарки", "gifts"));
        categories.add(new Pair("Развлечения", "fun"));
    }

    @Override
    public SendMessage process(Update update) {

        Message message = update.getMessage();
        User from = message.getFrom();

//        buffer.getSpendings()
//                .put(from.getId(), );

        SendMessage answer = new SendMessage();
        answer.setText(textMessage);
        //TODO NPE!!!
        answer.setChatId(update.getMessage()
                                 .getChatId());
        answer.enableHtml(true);

        InlineKeyboardMarkup replyMarkup = new InlineKeyboardMarkup();
        //TODO тут можно добавлять кнопки к сообщениям.
        replyMarkup.setKeyboard(getNextStepsButtons());
        answer.setReplyMarkup(replyMarkup);

        nextStepService.setNextStep(from.getId(), "setAmount");

        return answer;
    }

    private @NonNull List<List<InlineKeyboardButton>> getNextStepsButtons() {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++) {
            List<InlineKeyboardButton> buttonLine = new ArrayList<>();
            for (int j = i; j < i + 2; j++) {
                Pair pair = categories.get(j);
                InlineKeyboardButton button = new InlineKeyboardButton(pair.key);
                button.setCallbackData(pair.value);
                buttonLine.add(button);

            }
            i++;
            buttons.add(buttonLine);
        }

        return buttons;
    }


    @AllArgsConstructor
    class Pair{
        String key;
        String value;
    }
}
