package ru.nn.dvm.telegram.api.service.steps;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.nn.dvm.core.entity.Spending;
import ru.nn.dvm.core.service.EconomService;
import ru.nn.dvm.telegram.api.service.NextStepService;
import ru.nn.dvm.telegram.api.service.storage.SaleBuffer;

@Service("HauManySendStep")
@AllArgsConstructor
public class HauManySendStep implements StepService {

    private final EconomService economService;
    private final SaleBuffer buffer;
    private final NextStepService nextStepService;

    private final String textMessage = "Сколько денег вы потратили?";

    @Override
    public SendMessage process(Update update) {

        User from = update.getCallbackQuery()
                .getFrom();
        String data = update.getCallbackQuery()
                .getData();

        Spending spending = new Spending();
        spending.setCategory(data);

        buffer.getSpendings()
                .put(from.getId(), spending);

        SendMessage answer = new SendMessage();
        answer.setText(textMessage);
        //TODO NPE!!!
        answer.setChatId(update.getCallbackQuery()
                                 .getMessage()
                                 .getChatId());
        answer.enableHtml(true);

        nextStepService.setNextStep(from.getId(), "final_spending");

        return answer;
    }

}
