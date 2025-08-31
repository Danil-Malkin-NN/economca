package ru.nn.dvm.economca.service.steps;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface StepService {

	SendMessage process(Update update);

}
