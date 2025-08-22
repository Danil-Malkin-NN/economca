package ru.nn.dvm.telegram.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.nn.dvm.telegram.api.enumiration.MessageType.COMMAND;

@Service
@RequiredArgsConstructor
public class StepAgregator {

    private final Map<String, StepService> stepServices;

    public StepService getStepProcessor(Update update) {
        System.out.println(update);
        Message message = update.getMessage();
        List<MessageEntity> entities = message.getEntities();

        MessageEntity first = entities.getFirst();
        String type = first
                .getType();

        if(COMMAND.equals(type)){
            if(first.getText().equals("/start")){
                return stepServices.get("/start");
            }
        }


        return null;
    }
}
