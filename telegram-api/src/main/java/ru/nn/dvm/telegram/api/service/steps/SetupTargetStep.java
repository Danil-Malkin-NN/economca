package ru.nn.dvm.telegram.api.service.steps;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.nn.dvm.core.service.EconomService;

@Service(value = "/add_target")
@RequiredArgsConstructor
public class SetupTargetStep implements StepService {

    private final EconomService economService;

    @Override
    public SendMessage process(Update update) {

        SendMessage answer = new SendMessage();
        answer.setText("Введите команду и вашу цель без копеек в формате \"/target 50000\"");
        answer.setChatId(update.getCallbackQuery().getMessage().getChatId());
        answer.enableHtml(true);

        return answer;
    }
}
