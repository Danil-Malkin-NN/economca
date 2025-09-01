package ru.nn.dvm.telegram.api.service.steps;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.nn.dvm.core.service.EconomService;
import ru.nn.dvm.telegram.api.service.NextStepService;

@Service(value = "/add_target")
@RequiredArgsConstructor
public class SetupTargetStep implements StepService {

    private final EconomService economService;
    private final NextStepService nextStepService;

    @Override
    public SendMessage process(Update update) {

        SendMessage answer = new SendMessage();
        answer.setText("Введите вашу цель без копеек, например 50000 ");
        answer.setChatId(update.getCallbackQuery()
                                 .getMessage()
                                 .getChatId());
        answer.enableHtml(true);

        nextStepService.setNextStep(update.getCallbackQuery()
                                            .getFrom()
                                            .getId(), "setUpTarget");

        return answer;
    }
}
