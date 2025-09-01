package ru.nn.dvm.telegram.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.nn.dvm.telegram.api.service.steps.StepService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static ru.nn.dvm.telegram.api.enumiration.MessageType.COMMAND;

@Service
@RequiredArgsConstructor
public class StepAggregator {

    private final Map<String, StepService> stepServices;
    private final NextStepService nextStepService;

    public StepService getStepProcessor(Update update) {


        System.out.println(update);
        Message message = update.getMessage();
        if (message != null) {
            Optional<Long> id = Optional.ofNullable(message.getFrom())
                    .map(User::getId);
            if(id.isPresent()) {
                if(nextStepService.containsKey(id.get())) {
                    return stepServices.get(nextStepService.get(id.get()));
                }
            }

            List<MessageEntity> entities = message.getEntities();

            if(entities != null && !entities.isEmpty()) {
                MessageEntity first = entities.getFirst();
                String type = first.getType();

                if (COMMAND.equals(type)) {
                    String comand = first.getText();
                    return stepServices.get(comand);
                }
            }
            return stepServices.get(message.getText());

        } else if (update.getCallbackQuery() != null) {
            Optional<Long> id = Optional.ofNullable(update.getCallbackQuery().getFrom())
                    .map(User::getId);
            if(id.isPresent()) {
                if(nextStepService.containsKey(id.get())) {
                    return stepServices.get(nextStepService.get(id.get()));
                }
            }

            CallbackQuery callbackQuery = update.getCallbackQuery();
            return stepServices.get(callbackQuery.getData());
        }

        return null;
    }
}
