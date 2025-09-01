package ru.nn.dvm.telegram.api.service.steps;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.nn.dvm.core.service.EconomService;

@Service("setUpTarget")
@RequiredArgsConstructor
public class TargetMoneyStep implements StepService {

    private final EconomService economService;
    private final String textMessage = "Установлена цель по тратам %ЦЕЛЬ%";

    @Override
    public SendMessage process(Update update) {

        Message message = update.getMessage();
        User from = message.getFrom();

        String text = message.getText();
        String[] split = text.split(StringUtils.SPACE);
        var amount = Long.parseLong(split[split.length - 1]);

        economService.creteTarget(from.getId(), amount);

        String replace = textMessage.replace("%ЦЕЛЬ%", String.valueOf(amount));

        SendMessage answer = new SendMessage();
        answer.setText(replace);
        answer.setChatId(message.getChatId());
        answer.enableHtml(true);

        return answer;
    }
}
