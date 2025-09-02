package ru.nn.dvm.telegram.api.service.steps;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import ru.nn.dvm.core.entity.Spending;
import ru.nn.dvm.core.service.EconomService;
import ru.nn.dvm.telegram.api.service.storage.SaleBuffer;

@Service("final_spending")
@RequiredArgsConstructor
public class AmountStep implements StepService {

    private final EconomService economService;
    private final SaleBuffer saleBuffer;

    private final String textMessage = """
            Дневной остаток = %DAY%
            """;

    @Override
    public SendMessage process(Update update) {

        Message message = update.getMessage();
        User from = message.getFrom();

        Spending spending = saleBuffer.getSpendings()
                .get(from.getId());
        spending.setCount(Long.parseLong(message.getText()));

        long l = economService.addSpending(from.getId(), spending);

//        buffer.getSpendings()
//                .put(from.getId(), );

        SendMessage answer = new SendMessage();
        answer.setText(textMessage.replace("%DAY%", String.valueOf(l)));
        //TODO NPE!!!
        answer.setChatId(update.getMessage()
                                 .getChatId());
        answer.enableHtml(true);

        return answer;
    }
}
