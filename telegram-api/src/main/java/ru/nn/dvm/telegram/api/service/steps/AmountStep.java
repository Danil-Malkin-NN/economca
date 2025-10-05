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
            Общий остаток = %RESIDUUM%
            Дневной остаток = %DAY%
            """;

    @Override
    public SendMessage process(Update update) {

        Message message = update.getMessage();
        User from = message.getFrom();

        Long userTgId = from.getId();
        Spending spending = saleBuffer.getSpendings()
                .get(userTgId);
        spending.setCount(Long.parseLong(message.getText()));

        long l = economService.addSpending(userTgId, spending);

//        buffer.getSpendings()
//                .put(from.getId(), );

        SendMessage answer = new SendMessage();
        String resultMessage = textMessage.replace("%DAY%", String.valueOf(l))
                .replace("%RESIDUUM%", String.valueOf(economService.getTarget(userTgId)));

        answer.setText(resultMessage);
        //TODO NPE!!!
        answer.setChatId(update.getMessage()
                                 .getChatId());
        answer.enableHtml(true);

        return answer;
    }
}
