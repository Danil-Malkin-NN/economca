package ru.nn.dvm.telegram.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import ru.nn.dvm.core.service.EconomService;

@Service(value = "/add_target")
@RequiredArgsConstructor
public class SetupTargetServiceImpl implements StepService {

    private final EconomService economService;

    @Override
    public SendMessage process(Update update) {

        SendMessage answer = new SendMessage();
        answer.setText("Введите вашу цель без копеек например 50000");
        answer.setChatId(update.getCallbackQuery().getMessage().getChatId());
        answer.enableHtml(true);

        return answer;
    }
}
