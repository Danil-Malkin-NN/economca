package ru.nn.dvm.telegram.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.nn.dvm.core.service.EconomService;

@Service
@RequiredArgsConstructor
public class BysnesLogicService {

	private final EconomService economService;
	private final StepAgregator agregator;

	public SendMessage processAction(Update update) {

		StepService service = agregator.getStepProcessor(update);

		SendMessage message = service.process(update);


		return message;
	}
}
